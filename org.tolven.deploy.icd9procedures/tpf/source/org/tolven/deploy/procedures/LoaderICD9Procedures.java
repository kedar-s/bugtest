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
 * @version $Id: LoaderProcedures.java,v 1.1.2.1 2010/10/30 11:50:01 joseph_isaac Exp $
 */

package org.tolven.deploy.procedures;

import java.io.File;

import org.apache.log4j.Logger;
import org.tolven.plugin.TolvenCommandPlugin;

public class LoaderICD9Procedures extends TolvenCommandPlugin {

    protected Logger logger = Logger.getLogger(getClass());

    private String getAdminId() {
        return getTolvenConfigWrapper().getAdminId();
    }

    private char[] getAdminPassword() {
        return getTolvenConfigWrapper().getAdminPassword();
    }

    private String getAppRestfulRootURL() {
        return getTolvenConfigWrapper().getApplication().getAppRestfulURL();
    }

    private String getAuthRestfulRootURL() {
        return getTolvenConfigWrapper().getApplication().getAuthRestfulURL();
    }

    @Override
    protected void doStart() throws Exception {
        logger.debug("*** start ***");
    }

    @Override
    protected void doStop() throws Exception {
        logger.debug("*** stop ***");
    }

    @Override
    public void execute(String[] args) throws Exception {
        logger.debug("*** execute ***");
        LoadICD9Procedures loader = new LoadICD9Procedures(getAdminId(), getAdminPassword(), getAppRestfulRootURL(), getAuthRestfulRootURL());
        File src = getFilePath("tpf/voc/CMS29_DESC_LONG_SG.txt");
        logger.info("Load Procedures from " + src.getPath() + "...");
        loader.load(src.getPath());
        logger.info("Load Procedures completed");
    }

}
