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
 * @author Joseph Isaac
 * @version $Id: DefaultFSMutableStore.java 6283 2012-04-15 08:17:00Z joe.isaac $
 */
package org.tolven.document.store.fs;

import java.io.File;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.io.FileUtils;
import org.tolven.core.TolvenPropertiesLocal;
import org.tolven.doc.MutableStore;
import org.tolven.doc.StoredDocument;

public class DefaultFSMutableStore implements MutableStore {

    public static final String DOC_MUTABLE_STORE_DIRECTORIES = "org.tolven.document.store.fs.mutable-dirs";
    public static final String DOC_TMP_STORE_DIRECTORY = "org.tolven.document.store.fs.tmp-dir";
    public static final String FS = "FS";

    @Override
    public void cleanupMutable(String storeFormat, String storageMetadata) {
        if (storageMetadata != null) {
            File storageFile = new File(storageMetadata);
            FileUtils.deleteQuietly(storageFile);
        }
    }

    private File getDir(StoredDocument storedDocument, String documentStoreDirectories) {
        String documentStoreDirs = getPropertyBean().getProperty(documentStoreDirectories);
        if (documentStoreDirs == null) {
            throw new RuntimeException("Could not find property: " + documentStoreDirectories);
        }
        String[] dirs = documentStoreDirs.split(",");
        int index = (int) storedDocument.getId() % dirs.length;
        String dirname = dirs[index];
        return new File(dirname);
    }

    private String getId(StoredDocument storedDocument) {
        return String.valueOf(storedDocument.getId());
    }

    private File getMutableContentFile(StoredDocument storedDocument) {
        File storeDir = getMutableStoreDir(storedDocument);
        File contentFile = new File(storeDir, getId(storedDocument));
        return contentFile;
    }

    private File getMutableStoreDir(StoredDocument storedDocument) {
        return getDir(storedDocument, DOC_MUTABLE_STORE_DIRECTORIES);
    }

    private TolvenPropertiesLocal getPropertyBean() {
        String jndiName = null;
        try {
            InitialContext ctx = new InitialContext();
            jndiName = "java:app/tolvenEJB/TolvenProperties!org.tolven.core.TolvenPropertiesLocal";
            return (TolvenPropertiesLocal) ctx.lookup(jndiName);
        } catch (NamingException e) {
            throw new RuntimeException("Failed to lookup " + jndiName, e);
        }
    }

    private File getTmpFile(StoredDocument storedDocument) {
        String tmpStoreDirname = getPropertyBean().getProperty(DOC_TMP_STORE_DIRECTORY);
        return new File(tmpStoreDirname, String.valueOf(storedDocument.getId()));
    }

    private boolean isMutableStoreDirsNull() {
        return System.getProperty(DOC_MUTABLE_STORE_DIRECTORIES) == null;
    }

    @Override
    public String storeMutable(byte[] content, StoredDocument storedDocument) {
        if (content != null) {
            if (isMutableStoreDirsNull()) {
                throw new RuntimeException("Could not find property: " + DOC_MUTABLE_STORE_DIRECTORIES + " to store document:" + storedDocument.getId());
            } else {
                try {
                    File tmpContentFile = getTmpFile(storedDocument);
                    try {
                        FileUtils.writeByteArrayToFile(tmpContentFile, content);
                        File contentFile = getMutableContentFile(storedDocument);
                        FileUtils.copyFile(tmpContentFile, contentFile);
                        storedDocument.setStorageMetadata(contentFile.getPath());
                    } finally {
                        if (tmpContentFile.exists()) {
                            FileUtils.deleteQuietly(tmpContentFile);
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return FS;
    }

}
