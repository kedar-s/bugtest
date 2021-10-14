/*
 * Copyright (C) 2009 Tolven Inc

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
 * @version $Id: GenericXMLDocumentType.java 6197 2012-03-31 04:47:16Z joe.isaac $
 */

package org.tolven.doctype;

import org.tolven.core.entity.Status;
import org.tolven.doc.entity.DocBase;
import org.tolven.doc.entity.DocXML;
import org.tolven.el.ExpressionEvaluator;

public class GenericXMLDocumentType extends DocumentTypeAbstract {
    
    public final static String MEDIA_TYPE = "text/xml";

    public GenericXMLDocumentType() {
    }

    /**
     * Create a new document appropriate to this documentType
     * @return The document
     */
    public DocBase createNewDocument(String mediaType, String namespace) {
        DocXML doc = new DocXML();
        doc.setStatus(Status.NEW.value());
        doc.setMediaType(mediaType);
        doc.setXmlName("GenericXMLDocument");
        doc.setDocumentType(this);
        setDocument(doc);
        return doc;
    }

    @Override
    public Object getParsed() {
        return null;
    }

    public String getVariableName() {
        return "genericxml";
    }

    /**
     * Return true if this document is one that this DocumentType can handle
     */
    public boolean matchDocumentType(String mediaType, String namespace) {
        return (MEDIA_TYPE.equals(mediaType) && (namespace == null || namespace.trim().length() == 0));
    }

    public void prepareEvaluator(ExpressionEvaluator ee) {
    }

    @Override
    public String toString() {
        return "DocumentType: " + getVariableName() + " " + MEDIA_TYPE;
    }

}
