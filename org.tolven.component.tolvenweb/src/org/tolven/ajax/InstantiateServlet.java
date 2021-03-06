package org.tolven.ajax;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.security.jacc.PolicyContextException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.tolven.app.CreatorLocal;
import org.tolven.app.ListDataLocal;
import org.tolven.app.MDVersionDTO;
import org.tolven.app.MenuLocal;
import org.tolven.app.TrimLocal;
import org.tolven.app.bean.MenuPath;
import org.tolven.app.el.TrimExpressionEvaluator;
import org.tolven.app.entity.AccountMenuStructure;
import org.tolven.app.entity.ListQueryControl;
import org.tolven.app.entity.MenuData;
import org.tolven.app.entity.MenuQueryControl;
import org.tolven.app.entity.MenuStructure;
import org.tolven.core.TolvenPropertiesLocal;
import org.tolven.core.TolvenRequest;
import org.tolven.core.entity.AccountUser;
import org.tolven.core.entity.Status;
import org.tolven.doc.DocumentLocal;
import org.tolven.doc.entity.DocBase;
import org.tolven.doc.entity.DocXML;
import org.tolven.gatekeeper.client.api.RSGatekeeperClientLocal;
import org.tolven.logging.TolvenLogger;
import org.tolven.doc.DocProtectionLocal;
import org.tolven.security.key.UserPrivateKey;
import org.tolven.session.TolvenSessionWrapper;
import org.tolven.session.TolvenSessionWrapperFactory;
import org.tolven.trim.ActRelationship;
import org.tolven.trim.TrimMarshaller;
import org.tolven.trim.ValueSet;
import org.tolven.trim.ex.ActEx;
import org.tolven.trim.ex.ActRelationshipsMap;
import org.tolven.trim.ex.TRIMException;
import org.tolven.trim.ex.TrimEx;
import org.tolven.web.util.MiscUtils;

public class InstantiateServlet extends HttpServlet {
	private static final String TRIMns = "urn:tolven-org:trim:4.0";
	@EJB
    private CreatorLocal creatorBean;

    @EJB
    private MenuLocal menuBean;

    @EJB
    private DocumentLocal docBean;

    @EJB
    private DocProtectionLocal docProtectionBean;

    @EJB
    private TrimLocal trimBean;
    
    @EJB
    private TolvenPropertiesLocal propertyBean;
    
    @EJB
    private RSGatekeeperClientLocal rsGatekeeperClientBean;
    
    @EJB
    public ListDataLocal listDataLocal;

    private static TrimMarshaller trimMarshaller;
    
    public DocumentLocal getDocBean() {
        return docBean;
    }

    public DocProtectionLocal getDocProtectionBean() {
        return docProtectionBean;
    }

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		String uri = req.getRequestURI();
		AccountUser activeAccountUser = TolvenRequest.getInstance().getAccountUser();
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
	    resp.setHeader("Cache-Control", "no-cache");
	    Writer writer=resp.getWriter();
    	Date now = TolvenRequest.getInstance().getNow();
	    //	    writer.write( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );

        String keyAlgorithm = propertyBean.getProperty(UserPrivateKey.USER_PRIVATE_KEY_ALGORITHM_PROP);
        TolvenSessionWrapper sessionWrapper = TolvenSessionWrapperFactory.getInstance();
        PrivateKey userPrivateKey = sessionWrapper.getUserPrivateKey(keyAlgorithm);
	    if (uri.endsWith("sendCCR.ajaxi")) {
	    	long menuDataId = Long.parseLong(req.getParameter("patientId"));
	    	long otherAccount = Long.parseLong(req.getParameter("otherAccountId"));
	    	String rslt = "Send menuDataId " + menuDataId + " to " + otherAccount;
	    	MenuData md = menuBean.findMenuDataItem(menuDataId);
	    	writer.write( rslt );
    		TolvenLogger.info(rslt, InstantiateServlet.class);
            creatorBean.sendCopyTo(activeAccountUser, md.getDocumentId(), otherAccount, userPrivateKey);
    		req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
    	}

    	/**
    	 * In this sense, cancel means to delete the in-process document - we can remove all traces
    	 * since the document was not yet actionable so no audit is needed.
    	 */
	    else if (uri.endsWith("wizCancel.ajaxi")) {
			String element = req.getParameter( "element");
//			MenuPath path = new MenuPath( element );
//			TolvenLogger.info( "Milestone 1", InstantiateServlet.class );
			MenuData md  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
//			TolvenLogger.info( "Milestone 2", InstantiateServlet.class );
    		if (md.getStatus()==Status.NEW) {
    			menuBean.removeReferencingMenuData( md.getAccount().getId(), md.getDocumentId(), true);
    		}
//			TolvenLogger.info( "Milestone 3", InstantiateServlet.class );
    		// Confirm by returning the path of the element we deleted. (path==element)
	    	writer.write(md.getPath());
    		req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
    	}

    	// Browser requests TRIM XML
	    else if (uri.endsWith("trimGet.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			if (element == null ) throw new IllegalArgumentException( "Missing element in TRIM request");
			MenuData md = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			DocBase doc = getDocBean().findDocument( md.getDocumentId());
			writer.write(getDocProtectionBean().getDecryptedContentString(doc, activeAccountUser, userPrivateKey));
    		req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
	    }

    	// Count filtered menu data
	    else if (uri.endsWith("countMenuData.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			String filter = req.getParameter( "filter");
			MenuPath path = new MenuPath( element );
			MenuStructure ms=null;
			if(path.getPath().startsWith("global:"))
				ms =  menuBean.findGlobalMenuStructure(path.getPath() );
			else
				ms =  menuBean.findMenuStructure(activeAccountUser, path.getPath() );
			ListQueryControl ctrl = new ListQueryControl();
//			ctrl.setLimit( pageSize );
			ctrl.setMenuStructure( ms );
			ctrl.setAccountUser(activeAccountUser);
			ctrl.setNow( new Date() );
//			ctrl.setOffset( offset );
			ctrl.setOriginalTargetPath( path );
			ctrl.setRequestedPath( path );
			ctrl.setFilter(filter);
//			ctrl.setSortDirection( sortDir);
//			ctrl.setSortOrder( sortCol );
			Long menuDataCount = null;
			// Get the number of rows
			if(ms.getQuery() != null && (ms.getQuery().startsWith("query:")
					||ms.getQuery().startsWith("custom:"))){
				menuDataCount = new Long( listDataLocal.countListData(ctrl));
			}else{
				menuDataCount = new Long( menuBean.countMenuData( ctrl ) );
			}
			
			writer.write(Long.toString(menuDataCount));
    		req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
	    }

    	// Browser requests TRIM XML
	    else if (uri.endsWith("wizTransition.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			String action = req.getParameter( "action");
			if (element == null ) throw new IllegalArgumentException( "Missing element in TRIM request");
			if (action == null ) throw new IllegalArgumentException( "Missing action in TRIM transition request");
			TolvenLogger.info( "[wizTransition] account=" + activeAccountUser.getAccount().getId() + ", element=" + element + ", action: " + action, InstantiateServlet.class);
//			MenuData md = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
//			DocBase doc = docBean.findDocument( md.getDocumentId());
//			writer.write(docProtectionBean.getDecryptedContentString(doc, activeAccountUser));
			MenuData mdPrior  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			MenuData mdNew = creatorBean.createTRIMEvent( mdPrior, activeAccountUser, action, now, userPrivateKey );
    		// Confirm by returning the path of the element we deleted. (path==element)
			writer.write(mdPrior.getPath());
	    	writer.write(",");
	    	writer.write(mdNew.getPath());
 	    	req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
	    }
	    
	    else if (uri.endsWith("wizPHTransition.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			String action = req.getParameter( "action");
			if (element == null ) throw new IllegalArgumentException( "Missing element in TRIM request");
			if (action == null ) throw new IllegalArgumentException( "Missing action in TRIM transition request");
			TolvenLogger.info( "[wizPHTransition] account=" + activeAccountUser.getAccount().getId() + ", element=" + element + ", action: " + action, InstantiateServlet.class);
//			MenuData md = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
//			DocBase doc = docBean.findDocument( md.getDocumentId());
//			writer.write(docProtectionBean.getDecryptedContentString(doc, activeAccountUser));
			MenuData mdPrior  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			//MenuData mdNew = creatorBean.createTRIMEvent( mdPrior, activeAccountUser, action, now, userPrivateKey );
			MenuData mdPH = creatorBean.createPHEvent( mdPrior, action, userPrivateKey, element);
    		// Confirm by returning the path of the element we deleted. (path==element)
			writer.write(mdPrior.getPath());
	    	writer.write(",");
	    	writer.write(mdPH.getPath());
 	    	req.setAttribute("activeWriter", writer);
//			writer.close();
			return;
	    }
	    /**
	     * To nullify the current trim;
	     * @author vineetha
	     * Added on: 1/18/2011 
	     */

	    else if (uri.endsWith("wizNullify.ajaxi")) {
			String element = req.getParameter( "element");
			String action = req.getParameter( "action");
			MenuData md  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			MenuData mdNew = creatorBean.createTRIMEvent(md, activeAccountUser, action, now,userPrivateKey);
			creatorBean.submit(mdNew, activeAccountUser,userPrivateKey);
	    	writer.write(md.getPath());
    		req.setAttribute("activeWriter", writer);
    		return;
    	}


	    // Instantiation request from browser
	    else if (uri.endsWith("instantiate.ajaxi")) {
			String templateId = req.getParameter( "templateId");
			if (templateId == null ) throw new IllegalArgumentException( "Instantiation request has missing templateId");
			// Where called from
			String context = req.getParameter( "context");
			if (context == null ) throw new IllegalArgumentException( "Instantiation request has missing context");
			String source = req.getParameter( "source");
			if (source!=null && source.length()==0) {
				source = null;
			}
			MenuData md;
			TolvenLogger.info( "[InstantiateServlet] ms=" + templateId + " context: " + context, InstantiateServlet.class);
			md = creatorBean.createTRIMPlaceholder(activeAccountUser, templateId, context, now, source );	
			TolvenLogger.info("Create new event:" + md.getPath(), InstantiateServlet.class);
			writer.write(md.getPath());
    		req.setAttribute("activeWriter", writer);
			return;
	    } else if (uri.endsWith("instantiateTrans.ajaxi")) {
			String templateId = req.getParameter( "templateId");
			if (templateId == null ) throw new IllegalArgumentException( "Instantiation request has missing templateId");
			// Where called from
			String context = req.getParameter( "context");
			if (context == null ) throw new IllegalArgumentException( "Instantiation request has missing context");
			String source = req.getParameter( "source");
			if (source!=null && source.length()==0) {
				source = null;
			}
			MenuData md;
			TolvenLogger.info( "[InstantiateServlet] ms=" + templateId + " context: " + context, InstantiateServlet.class);
			String actStatus = req.getParameter( "actStatus");
			if(actStatus!=null || !actStatus.equals("")) {
				MenuData mdPrior = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), context);
				md = creatorBean.createTRIMPlaceholder(activeAccountUser, templateId, context, now, source, actStatus );
				writer.write(mdPrior.getPath());
		    	writer.write(",");				
			} else {
				md = creatorBean.createTRIMPlaceholder(activeAccountUser, templateId, context, now, source );	
			}
			TolvenLogger.info("Create new event:" + md.getPath(), InstantiateServlet.class);
			writer.write(md.getPath());
    		req.setAttribute("activeWriter", writer);
			return;
	    } else if (uri.endsWith("instantiateGrid.ajaxi")) {
			String menuPath = req.getParameter( "menuPath");
			String disableFavorites = req.getParameter("disableFavorites");
			if (menuPath == null ) throw new IllegalArgumentException( "Instantiation request has missing menuPath");
			// Where called from
			String context = req.getParameter( "context");
			if (StringUtils.isBlank(context)){
				context = menuPath;
			}					
			String source = req.getParameter( "source");
			if (source!=null && source.length()==0) {
				source = null;
			}			
			MenuPath path = new MenuPath( menuPath );
			AccountMenuStructure ms = menuBean.findAccountMenuStructure(activeAccountUser.getAccount().getId(), path.getPath() );
			MenuQueryControl ctrl = new MenuQueryControl();
			ctrl.setMenuStructure( ms );
			ctrl.setNow( new Date() );
			ctrl.setOriginalTargetPath( path );
			ctrl.setLimit(2);
			ctrl.setRequestedPath( path );
			List<MenuData> menuDataList = menuBean.findMenuData(ctrl);	    	
	    	if(menuDataList.size() == 1){
	    		//instantiate the wizard
	    		MenuData placeHolder = menuDataList.get(0);	    		
				TolvenLogger.info( "[InstantiateServlet] ms=" + placeHolder.getPath() + " context: " + context, InstantiateServlet.class);
				MenuData md = creatorBean.createTRIMPlaceholder(activeAccountUser, placeHolder.getPath(), context, now, source );
				TolvenLogger.info("Create new event:" + md.getPath(), InstantiateServlet.class);
				writer.write("<response><type>wizard</type>");
				writer.write("<span>"+md.getPath()+"</span></response>");	    		
	    	}else{ //create a grid and write the html to writer	
	    		//reset the limit
	    		ctrl.setLimit(0);
	    		long menuDataSize = menuBean.countMenuData(ctrl);
	    		String gridId = req.getParameter("gridId");	    	
				// java script method for grid rows on-click
				String scriptMethodName = req.getParameter("method");
				// Form id for .   
				String scriptMethodArgs = req.getParameter("methodArgs");
				GridBuilder grid = new GridBuilder(ctrl,menuBean, menuDataSize);
				menuBean.prepareMQC( ctrl );
    			if(null == disableFavorites || disableFavorites.equals("false")){
	    			grid.setFavorites(MiscUtils.findFavorites(path.getPath(), activeAccountUser, menuBean, path));
	    			// need this to add 'All' on the favorites tabs
	    			if(grid.getFavorites().size() > 0)
	    				grid.setFavoritesType(activeAccountUser.getAccount().getAccountType().getKnownType()+
	    						":"+(String)grid.getFavorites().get(0).get("Type"));
	    		}

				if(gridId != null)
					grid.setGridId(gridId);
				grid.createGrid("instantiate", menuPath );
				writer.write("<response><type>grid</type>");
				writer.write("<span>"+grid.toString()+"</span></response>");
	    	}
			req.setAttribute("activeWriter", writer);
			return;
	    }
	    // Edit trim
	    else if (uri.endsWith("insertChoice.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			String path = req.getParameter( "path");
			String choice = req.getParameter( "choice");
			if (element == null ) throw new IllegalArgumentException( "Missing element in insertChoice");
			if (path == null ) throw new IllegalArgumentException( "Missing path in TRIM insertChoice");
			if (choice == null ) throw new IllegalArgumentException( "Missing choice in TRIM insertChoice");
			TolvenLogger.info( "[insertChoice] account=" + activeAccountUser.getAccount().getId() + ", element=" + element + ", path: " + path + ", choice:" + choice, InstantiateServlet.class);
			MenuData md  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			DocXML docXML = (DocXML) getDocBean().findDocument(md.getDocumentId());
			// Note: We're reading this in from a mutable document.
			String trimString = this.getDocProtectionBean().getDecryptedContentString(docXML, activeAccountUser, userPrivateKey);

			// Unmarshall
			TrimEx trim = (TrimEx) getTrimMarshaller().unmarshal(TRIMns, new StringReader( trimString) );
//			Trim trim = trimBean.parseTrim( trimString, null );
			
			TrimEx newTrim = trimBean.findTrim(choice);
			// Merge valueSets as well
			for (ValueSet vs : newTrim.getValueSets()) {
				trim.getValueSets().add(vs);
			}
			ActRelationship ar = trimBean.findActRelationship( trim, path );
			boolean foundNewHome = true;
			if (ar.getAct()!=null) {
				foundNewHome = false;
				for (ActRelationship newAr : newTrim.getAct().getRelationships()) {
					if ("nextStep".equals(newAr.getName()) && newAr.getAct()==null) {
						newAr.setAct(ar.getAct());
						foundNewHome = true;
						break;
					}
				}
			}
			if (!foundNewHome) throw new RuntimeException( "Could not find a new home for the displaced next step");
			// OK, now add the new trim act.
			ar.setAct(newTrim.getAct());
			// Write it back and we're done.
			creatorBean.marshalToDocument( trim, docXML );
			writer.write(element);
    		req.setAttribute("activeWriter", writer);
    		//writer.close();
			return;
	    } else if (uri.endsWith("copyTrimElement.ajaxi")) {
//		    writer.write( "<element>" );
	    	// The MenuStructureItem to use
			String element = req.getParameter( "element");
			String path = req.getParameter( "path");
			String choice = req.getParameter( "choice");
			if (element == null ) throw new IllegalArgumentException( "Missing element in copyTrimElement");
			if (path == null ) throw new IllegalArgumentException( "Missing path in TRIM copyTrimElement");
			if (choice == null ) throw new IllegalArgumentException( "Missing choice in TRIM copyTrimElement");
			TolvenLogger.info( "[insertTrimElement] account=" + activeAccountUser.getAccount().getId() + ", element=" + element + ", path: " + path + ", choice:" + choice, InstantiateServlet.class);
			MenuData md  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			DocXML docXML = (DocXML) getDocBean().findDocument(md.getDocumentId());
			// Note: We're reading this in from a mutable document.
			String trimString = this.getDocProtectionBean().getDecryptedContentString(docXML, activeAccountUser, userPrivateKey);

			// Unmarshall
			TrimEx trim = (TrimEx) getTrimMarshaller().unmarshal(TRIMns, new StringReader( trimString) );
//			Trim trim = trimBean.parseTrim( trimString, null );
			
			TrimEx newTrim = trimBean.findTrim(choice);
			// Merge valueSets as well
			for (ValueSet vs : newTrim.getValueSets()) {
				trim.getValueSets().add(vs);
			}
			ActRelationship ar = ((ActEx)trim.getAct()).getRelationship().get(path);
			ar.getAct().getRelationships().addAll(newTrim.getAct().getRelationships());
			
			// Write it back and we're done.
			creatorBean.marshalToDocument( trim, docXML );
			writer.write(element);
    		req.setAttribute("activeWriter", writer);
    		//writer.close();
			return;
	    }else  if( uri.endsWith( "copyMenuDataToList.ajaxi")){
	    	//SK TODO: may be I can use harvest instead of this. 
	    	//srcMSPath="+srcGridId+"&destMSPath="+destGridId+"&menuDataList="+selectedItemsIds, 
	    	String element = req.getParameter("element");
	    	String template = req.getParameter("template");
	    	boolean isDelete = Boolean.parseBoolean(req.getParameter("isDelete"));	
	    	String[] menuDataList = req.getParameter("menuDataList").split("-");
	    	MenuData md  = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
			DocXML docXML = (DocXML) getDocBean().findDocument(md.getDocumentId());
			// Note: We're reading this in from a mutable document.
			String trimString = this.getDocProtectionBean().getDecryptedContentString(docXML, activeAccountUser, userPrivateKey);
			// Unmarshall
			TrimEx wizardTrim = (TrimEx) getTrimMarshaller().unmarshal(TRIMns, new StringReader( trimString) );
	     	
	    	if(!isDelete){
		    	MenuData item= menuBean.findMenuDataItem(Long.parseLong(menuDataList[1]));	
		     	TrimEx trimEx = trimBean.findTrim(template);
		     	ActRelationship ar = trimEx.getAct().getRelationships().get(0);
		     	ar.setSourceTrim(item.getPath());
		     	
		     	TrimExpressionEvaluator evaluator = new TrimExpressionEvaluator();
		     	evaluator.addVariable("act",ar.getAct());
		     	evaluator.setValue("#{act.title.ST.value}", item.getString01());
		     	evaluator.setValue("#{act.relationship['path'].act.title.ST.value}", item.getString02());
		     	wizardTrim.getAct().getRelationships().add(ar);		     	
	     	}else{ // try deleting the menudata if exists. 
	     		//TODO: may not be need after fixind AppEvalAdaptor to remove the menudata for a given doc ID.
	     		List<ActRelationship> arList =  ((ActEx)wizardTrim.getAct()).getRelationshipsList().get("favoriteItem");
	     		for(int i=0;i<arList.size();i++){
	     			if(arList.get(i).getSourceTrim().equals(req.getParameter("menuDataList"))){
	     				ActRelationship toRemove = arList.remove(i);
	     				String menuPath = req.getParameter("menuDataId");
	     				if(menuPath == null || menuPath.equals("undefined"))
	     					continue;
	     				MenuData deleteMD = menuBean.findMenuDataItem(Long.parseLong(menuPath.substring(menuPath.lastIndexOf("-")+1)));
	     				if(deleteMD == null)
	     					continue;
	     				deleteMD.setDeleted(true);
	     				menuBean.removeReferencingMenuData(deleteMD);
	     				menuBean.persistMenuData(deleteMD);	     				
	     			}
	     		}
	     		((ActRelationshipsMap)((ActEx)wizardTrim.getAct()).getRelationshipsList()).refreshList();
	     	}
	     	creatorBean.marshalToDocument( wizardTrim, docXML );
			writer.write("Success");
    		req.setAttribute("activeWriter", writer);
      }
	    
	} catch (JAXBException e) {
		throw new ServletException( "JAXB Exception thrown in IntantiateServlet", e);
	} catch (TRIMException e) {
		throw new ServletException( "TRIM Exception thrown in IntantiateServlet", e);
	} catch (Exception e) {
		throw new ServletException( "Exception thrown in IntantiateServlet", e);
	}
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String uri = request.getRequestURI();
	AccountUser accountUser = TolvenRequest.getInstance().getAccountUser();
	response.setContentType("application/octet-stream");
	response.setHeader("Cache-Control", "no-cache");
    Writer writer=response.getWriter();
	Date now = TolvenRequest.getInstance().getNow();

	// Browser puts TRIM XML
    if (uri.endsWith("trimPut.ajaxi")) {
//		    writer.write( "<element>" );
    	// The MenuStructureItem to use
		String element = request.getParameter( "element");
		if (element == null ) throw new IllegalArgumentException( "Missing element in TRIM request");
		request.getInputStream();
//			    writer.write( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<html>\n" +
	}

    // Check versions of requested elements (if the feature is disabled in account, then menuBean returns no versions.
    if (uri.endsWith("versionCheck.ajaxi")) {
    	Set<String> keys = request.getParameterMap().keySet();
    	if (keys.contains("accountUserId")) {
    		keys.remove("accountUserId");
    	}
    	if (keys.size()>0) {
    		List<String> elements = new ArrayList<String>(keys);
	    	List<MDVersionDTO> mdvs = menuBean.findMenuDataVersions(accountUser.getAccount(), elements);
	    	//TolvenLogger.info("Sent "+keys.size()+"  found "+ mdvs.size(), InstantiateServlet.class);
	    	boolean firstTime = true;
	    	for (MDVersionDTO mdv : mdvs ) {
	    		long version = Long.parseLong(request.getParameter(mdv.getElement()));
	    		if (mdv.getVersion() > version) {
	    			if (firstTime) firstTime=false;
	    			else writer.write( ",");
	    	    	writer.write( mdv.getElement() );
	    	    	writer.write( "=" );
	    	    	writer.write( Long.toString(mdv.getVersion()) );
	    		}
	    	}
    	}
		request.setAttribute("activeWriter", writer);
//		writer.close();
		return;
	}
    /**
     * We have the data, now just submit to make it actionable.
     */
        if (uri.endsWith("wizSubmit.ajaxi")) {
            String element = request.getParameter("element");
            TolvenLogger.info("[wizSubmit] " + element, InstantiateServlet.class);
            AccountUser activeAccountUser = TolvenRequest.getInstance().getAccountUser();
            MenuData md = menuBean.findMenuDataItem(activeAccountUser.getAccount().getId(), element);
            if (md.getStatus() == Status.NEW) {
                DocBase document = getDocBean().findDocument(md.getDocumentId());
                try {
                    String keyAlgorithm = propertyBean.getProperty(UserPrivateKey.USER_PRIVATE_KEY_ALGORITHM_PROP);
                    TolvenSessionWrapper sessionWrapper = TolvenSessionWrapperFactory.getInstance();
                     PrivateKey userPrivateKey = sessionWrapper.getUserPrivateKey(keyAlgorithm);
                    if (document.isSignatureRequired()) {
                        String password = request.getParameter("password");
                        if (password == null || password.trim().length() == 0) {
                            response.sendError(403, "To sign a document, the signer's password is required");
                            return;
                        }
                        String principal = (String) sessionWrapper.getPrincipal();
                        String realm = sessionWrapper.getRealm();
                        boolean passwordVerfied = rsGatekeeperClientBean.verifyUserPassword(principal, password.toCharArray(), realm);
                        if (!passwordVerfied) {
                            response.sendError(401, "Incorrect password");
                            return;
                        }
                        getDocProtectionBean().sign(document, activeAccountUser, userPrivateKey, sessionWrapper.getUserX509Certificate());
                    }
                    creatorBean.submitNow(md, activeAccountUser, now, userPrivateKey);
                    // Confirm by returning the path of the element we submitted. (path==element)
                    writer.write(md.getPath());
                } catch (NamingException ex) {
                    throw new ServletException(ex);
                } catch (GeneralSecurityException ex) {
                    throw new ServletException(ex);
                } catch (PolicyContextException ex) {
                    throw new ServletException(ex);
                } catch (Exception ex) {
                    throw new ServletException(ex);
                }
            }
        }

    }

    private TrimMarshaller getTrimMarshaller() {
        if (trimMarshaller == null) {
            trimMarshaller = new TrimMarshaller();
        }
        return trimMarshaller;
    }

}
