/*
 * Copyright (C) 2010 Tolven Inc

 * This library is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU Lesser General Public License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;  
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 *
 * Contact: info@tolvenhealth.com 
 *
 * @author <your name>
 * @version $Id: DocumentResources.java 6788 2012-07-16 19:11:55Z joe.isaac $
 */

package org.tolven.api.rs.resource;

import java.io.StringWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Logger;
import org.tolven.app.DataExtractLocal;
import org.tolven.app.bean.MenuPath;
import org.tolven.app.entity.AccountMenuStructure;
import org.tolven.app.entity.MenuData;
import org.tolven.core.TolvenPropertiesLocal;
import org.tolven.core.TolvenRequest;
import org.tolven.core.entity.Account;
import org.tolven.core.entity.AccountUser;
import org.tolven.doc.DocProtectionLocal;
import org.tolven.doc.DocumentLocal;
import org.tolven.doc.entity.DocBase;
import org.tolven.msg.ProcessLocal;
import org.tolven.msg.TolvenMessageSchedulerLocal;
import org.tolven.security.key.DocumentSecretKey;
import org.tolven.security.key.UserPrivateKey;
import org.tolven.session.TolvenSessionWrapper;
import org.tolven.session.TolvenSessionWrapperFactory;
import org.tolven.util.ExceptionFormatter;

@Path("document")
@ManagedBean
public class DocumentResources {

    @EJB
    private DataExtractLocal dataExtractBean;

    @EJB
    private DocProtectionLocal docProtectionBean;
    
    @EJB
    private DocumentLocal documentBean;

    private static Logger logger = Logger.getLogger(DocumentResources.class);

    @EJB
    private ProcessLocal processBean;

	@EJB
    private TolvenPropertiesLocal propertyBean;

    private @EJB
    TolvenMessageSchedulerLocal tmSchedulerBean;
    
    /**
     * Create a document
     * @return response
     */
    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createDocument(
            @FormParam("mediaType") String mediaType,
            @FormParam("namespace") String namespace,
            @FormParam("payload") String payload) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        DocBase doc = getDocumentBean().createNewDocument(mediaType, namespace, accountUser);
        logger.info("Document created, id: " + doc.getId() + " Account: " + doc.getAccount().getId());
        String kbeKeyAlgorithm = getPropertyBean().getProperty(DocumentSecretKey.DOC_KBE_KEY_ALGORITHM_PROP);
        int kbeKeyLength = Integer.parseInt(getPropertyBean().getProperty(DocumentSecretKey.DOC_KBE_KEY_LENGTH));
        byte[] bytes = null;
        if(payload != null) {
            bytes = payload.getBytes("UTF-8");
        }
        doc.setAsEncryptedContent(bytes, kbeKeyAlgorithm, kbeKeyLength);
        doc.setFinalSubmitTime(TolvenRequest.getInstance().getNow());
        Response response = Response.ok().entity(String.valueOf(doc.getId())).build();
        return response;
    }

    /** 
     * Return placeholder, list based on document ID.
     * Type (placeholder or list) is optional.
     */
    @Path("referencedBy")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getReferencedBy(
            @QueryParam("documentId") String documentId,
            @QueryParam("role") @DefaultValue("") String roleFilter) throws Exception {
        Account account = TolvenRequest.getInstance().getAccount();
        if (account == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("Account not found").build();
        }
        if (documentId == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("documentId is required").build();
        }
        StringWriter sw = new StringWriter();
        XMLStreamWriter xmlStreamWriter = null;
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            xmlStreamWriter = factory.createXMLStreamWriter(sw);
            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("results");
            xmlStreamWriter.writeAttribute("document", documentId.toString());
            xmlStreamWriter.writeAttribute("account", Long.toString(account.getId()));
            xmlStreamWriter.writeAttribute("database", getPropertyBean().getProperty("tolven.repository.oid"));
            GregorianCalendar now = new GregorianCalendar();
            now.setTime(TolvenRequest.getInstance().getNow());
            DatatypeFactory xmlFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar ts = xmlFactory.newXMLGregorianCalendar(now);
            xmlStreamWriter.writeAttribute("timestamp", ts.toXMLFormat());
            List<MenuData> mdList = getDataExtractBean().findMenuDataByDocumentId(account, Long.parseLong(documentId));
            Iterator<MenuData> it = mdList.iterator();
            while (it.hasNext()) {
                MenuData md = (MenuData) it.next();
                String path = md.getPath();
                MenuPath mp = new MenuPath(path);
                AccountMenuStructure ms = getDataExtractBean().findAccountMenuStructure(account, mp);
                String role = ms.getRole();
                roleFilter = roleFilter.toLowerCase();
                if (roleFilter.equals("") || roleFilter.equals(role)) {
                    xmlStreamWriter.writeStartElement("row");
                    xmlStreamWriter.writeStartElement("path");
                    xmlStreamWriter.writeCharacters(path);
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeStartElement("role");
                    xmlStreamWriter.writeCharacters(role);
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeEndElement();
                }
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN_TYPE).entity(ExceptionFormatter.toSimpleString(e, "\n")).build();
        } finally {
            if (xmlStreamWriter != null) {
                try {
                    xmlStreamWriter.close();
                } catch (XMLStreamException e) {
                }
            }
        }
        Response response = Response.ok().entity(sw.toString()).build();
        return response;
    }

    private DataExtractLocal getDataExtractBean() {
        if (dataExtractBean == null) {
            String jndiName = "java:app/tolvenEJB/DataExtractBean!org.tolven.app.DataExtractLocal";
            try {
                InitialContext ctx = new InitialContext();
                dataExtractBean = (DataExtractLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
        return dataExtractBean;
    }

    private DocProtectionLocal getDocProtectionBean() {
    	if (docProtectionBean == null) {
            String jndiName = "java:app/tolvenEJB/DocProtectionBean!org.tolven.doc.DocProtectionLocal";
            try {
                InitialContext ctx = new InitialContext();
                docProtectionBean = (DocProtectionLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
  		return docProtectionBean;
  	}

    private DocumentLocal getDocumentBean() {
        if (documentBean == null) {
            String jndiName = "java:app/tolvenEJB/DocumentBean!org.tolven.doc.DocumentLocal";
            try {
                InitialContext ctx = new InitialContext();
                documentBean = (DocumentLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
        return documentBean;
    }

    private TolvenMessageSchedulerLocal getTolvenMessageSchedulerBean() {
        if (tmSchedulerBean == null) {
            String jndiName = "java:app/tolvenEJB/TolvenMessageScheduler!org.tolven.msg.TolvenMessageSchedulerLocal";
            try {
                InitialContext ctx = new InitialContext();
                tmSchedulerBean = (TolvenMessageSchedulerLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
        return tmSchedulerBean;
    }

    @Path("body")
    @GET
    public Response getDocumentBody(@QueryParam("id") String id) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        DocBase doc = getDocumentBean().findDocument(Long.parseLong(id));
        if (doc.getAccount().getId() != accountUser.getAccount().getId()) {
            return Response.status(Status.FORBIDDEN).type(MediaType.TEXT_PLAIN).entity("Document not found in this account").build();
        }
        String keyAlgorithm = getPropertyBean().getProperty(UserPrivateKey.USER_PRIVATE_KEY_ALGORITHM_PROP);
        TolvenSessionWrapper sessionWrapper = TolvenSessionWrapperFactory.getInstance();
        String body = getDocProtectionBean().getDecryptedContentString(doc, accountUser, sessionWrapper.getUserPrivateKey(keyAlgorithm));
        Response response = Response.ok().type(doc.getMediaType()).entity(body).build();
        return response;
    }

    @Path("header")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getDocumentHeader(@QueryParam("id") String id) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        DocBase doc = getDocumentBean().findDocument(Long.parseLong(id));
        if (doc.getAccount().getId() != accountUser.getAccount().getId()) {
            return Response.status(Status.FORBIDDEN).type(MediaType.TEXT_PLAIN).entity("Document not found in this account").build();
        }
        Response response = Response.ok().entity(doc).build();
        return response;
    }

    @Path("signature")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDocumentSignature(@QueryParam("id") String id) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        DocBase doc = getDocumentBean().findDocument(Long.parseLong(id));
        if (doc.getAccount().getId() != accountUser.getAccount().getId()) {
            return Response.status(Status.FORBIDDEN).type(MediaType.TEXT_PLAIN).entity("Document not found in this account").build();
        }
        String keyAlgorithm = getPropertyBean().getProperty(UserPrivateKey.USER_PRIVATE_KEY_ALGORITHM_PROP);
        TolvenSessionWrapper sessionWrapper = TolvenSessionWrapperFactory.getInstance();
        String signature = getDocProtectionBean().getDocumentSignaturesString(doc, accountUser, sessionWrapper.getUserPrivateKey(keyAlgorithm));
        if (signature == null || signature.length() == 0) {
            return Response.noContent().build();
        }
        Response response = Response.ok().entity(signature).build();
        return response;
    }

    private ProcessLocal getProcessLocal() {
  		if (processBean == null) {
            String jndiName = "java:app/tolvenEJB/ProcessBean!org.tolven.msg.ProcessLocal";
            try {
                InitialContext ctx = new InitialContext();
                processBean = (ProcessLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
  		return processBean;
  	}

    private TolvenPropertiesLocal getPropertyBean() {
  		if (propertyBean == null) {
            String jndiName = "java:app/tolvenEJB/TolvenProperties!org.tolven.core.TolvenPropertiesLocal";
            try {
                InitialContext ctx = new InitialContext();
                propertyBean = (TolvenPropertiesLocal) ctx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup " + jndiName);
            }
        }
  		return propertyBean;
  	}

    /**
     * Create a document
     * @return response
     */
    @Path("process/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processDocument(@PathParam("id") String id) throws Exception {
        getProcessLocal().processDocument(Long.parseLong(id), new Date());
        return Response.ok().build();
    }
    
    /**
    * Process a document (synchronously)
    * @return response
    */
    @Path("process")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processDocument(@DefaultValue("urn:astm-org:CCR") @FormParam("xmlns") String xmlns, @DefaultValue("text/xml") @FormParam("mediaType") String mediaType, @FormParam("payload") String payload) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        long documentId = getProcessLocal().processMessage(payload.getBytes(), mediaType, xmlns, accountUser.getAccount().getId(), accountUser.getUser().getId(), new Date());
        URI uri = null;
        try {
            uri = new URI(URLEncoder.encode(Long.toString(documentId), "UTF-8"));
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(ExceptionFormatter.toSimpleString(e, "\\n")).build();
        }
        Response response = Response.created(uri).entity(String.valueOf(documentId)).build();
        return response;
    }
    
    /**
     * Create a document
     * @return response
     */
    @Path("processXML/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processXMLDocument(@PathParam("id") String id) throws Exception {
        getProcessLocal().processXMLDocument(Long.parseLong(id), new Date());
        return Response.ok().build();
    }

  	/**
     * Submit a document for processing
     * @return response
     */
    @Path("submit")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response submitDocument(@DefaultValue("urn:astm-org:CCR") @FormParam("xmlns") String xmlns, @FormParam("payload") String payload) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        getTolvenMessageSchedulerBean().queueWSMessage(payload.getBytes(), xmlns, accountUser.getAccount().getId(), accountUser.getUser().getId());
        Response response = Response.ok().entity("Document submitted").build();
        return response;
    }

  	/**
     * Create a document
     * @return response
     */
    @Path("update")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateDocument(
            @FormParam("id") String id,
            @FormParam("payload") String payload) throws Exception {
        AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
        if (accountUser == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("AccountUser not found").build();
        }
        DocBase docBase = getDocumentBean().findDocument(Long.parseLong(id));
        if (docBase.getAccount().getId() != accountUser.getAccount().getId()) {
            return Response.status(Status.FORBIDDEN).type(MediaType.TEXT_PLAIN).entity("Document not found in this account").build();
        }
        String kbeKeyAlgorithm = getPropertyBean().getProperty(DocumentSecretKey.DOC_KBE_KEY_ALGORITHM_PROP);
        int kbeKeyLength = Integer.parseInt(getPropertyBean().getProperty(DocumentSecretKey.DOC_KBE_KEY_LENGTH));
        byte[] bytes = null;
        if(payload != null) {
            bytes = payload.getBytes("UTF-8");
        }
        docBase.setAsEncryptedContent(bytes, kbeKeyAlgorithm, kbeKeyLength);
        docBase.setFinalSubmitTime(TolvenRequest.getInstance().getNow());
        Response response = Response.ok().build();
        return response;
    }

}
