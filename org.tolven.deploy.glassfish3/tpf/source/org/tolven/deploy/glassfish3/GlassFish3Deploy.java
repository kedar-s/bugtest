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
 * @version $Id: GlassFish3Deploy.java 2249 2011-08-05 22:17:59Z joe.isaac $
 */
package org.tolven.deploy.glassfish3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.tolven.naming.JndiManager;
import org.tolven.naming.JndiManagerFactory;
import org.tolven.plugin.TolvenCommandPlugin;
import org.tolven.security.hash.TolvenMessageDigest;
import org.tolven.tools.ant.TolvenExecute;

/**
 * This plugin deploys the JBoss configuration files.
 * 
 * @author Joseph Isaac
 *
 */
public class GlassFish3Deploy extends TolvenCommandPlugin {

    public static final String ATTR_BIN_DIR = "binDir";
    public static final String ATTR_STAGE_DEPLOY_DIR = "deployDir";
    public static final String CMD_JNDI = "jndi";
    public static final String CMD_LINE_CONFIG_OPTION = "config";
    public static final String CMD_LINE_EAR_PLUGINS_OPTION = "earPlugins";
    public static final String CMD_LINE_RAR_PLUGINS_OPTION = "rarPlugins";

    public static final String MESSAGE_DIGEST_ALGORITHM = "md5";

    private Logger logger = Logger.getLogger(GlassFish3Deploy.class);

    private void assembleJNDI(Properties srcProperties) {
        try {
            JndiManager jndiManager = JndiManagerFactory.getInstance();
            Properties jndiProperties = jndiManager.getJndiProperties(srcProperties);
            String appserverHomeDirname = getPluginsWrapper().getProperties().getProperty("appserver.home");
            File appserverBinDir = new File(appserverHomeDirname, "bin");
            if (!appserverBinDir.exists()) {
                throw new RuntimeException("appserver home does not exist at: " + appserverBinDir.getPath());
            }
            File asadmin = null;
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") == -1) {
                asadmin = new File(appserverBinDir, "asadmin");
            } else {
                asadmin = new File(appserverBinDir, "asadmin.bat");
            }
            Properties escapedJndiProperties = escape(jndiProperties);

            String[] deleteCommandLine = new String[3];
            deleteCommandLine[0] = asadmin.getPath();
            deleteCommandLine[1] = "delete-custom-resource";
            deleteCommandLine[2] = JndiManager.TOLVENCONTEXT_ID_REF;
            String appserverPathname = getPluginsWrapper().getProperties().getProperty("appserver.home");
            File appserverDir = new File(appserverPathname);
            String relativeBinDirPath = getDescriptor().getAttribute(ATTR_BIN_DIR).getValue();
            File binDir = new File(appserverDir, "/" + relativeBinDirPath);
            int exitValue = new TolvenExecute().execute(deleteCommandLine, binDir);
            if (exitValue != 0) {
                logger.info("Will create-custom-resource:" + JndiManager.TOLVENCONTEXT_ID_REF);
            }
            String[] createCommandLine = new String[11];
            createCommandLine[0] = asadmin.getPath();
            createCommandLine[1] = "create-custom-resource";
            createCommandLine[2] = "--description";
            createCommandLine[3] = "\"TolvenContext\"";
            createCommandLine[4] = "--restype";
            createCommandLine[5] = "org.tolven.naming.TolvenContext";
            createCommandLine[6] = "--factoryclass";
            createCommandLine[7] = "org.tolven.naming.TolvenContextFactory";
            createCommandLine[8] = "--property";
            createCommandLine[9] = getASAdminPropertyString(escapedJndiProperties);
            createCommandLine[10] = JndiManager.TOLVENCONTEXT_ID_REF;
            appserverPathname = getPluginsWrapper().getProperties().getProperty("appserver.home");
            appserverDir = new File(appserverPathname);
            relativeBinDirPath = getDescriptor().getAttribute(ATTR_BIN_DIR).getValue();
            binDir = new File(appserverDir, "/" + relativeBinDirPath);
            exitValue = new TolvenExecute().execute(createCommandLine, binDir);
            if (exitValue != 0) {
                throw new RuntimeException("Failed to execute asadmin command");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not execute asadmin jndi command", ex);
        }
    }

    protected void deploy(Collection<File> filesToDeploy, File sourceDir, File appserverHomeDir) throws Exception {
        String relativeDeployDirPath = getDescriptor().getAttribute(ATTR_STAGE_DEPLOY_DIR).getValue();
        File deployDir = new File(appserverHomeDir, relativeDeployDirPath);
        for (File stageFile : filesToDeploy) {
            String relativeStageFilename = stageFile.getPath().substring(1 + sourceDir.getPath().length());
            File deployedFile = new File(deployDir, relativeStageFilename);
            if (deployedFile.exists()) {
                String stageFileDigest = TolvenMessageDigest.checksum(stageFile.toURI().toURL(), MESSAGE_DIGEST_ALGORITHM);
                String deployedFileDigest = TolvenMessageDigest.checksum(deployedFile.toURI().toURL(), MESSAGE_DIGEST_ALGORITHM);
                if (deployedFileDigest.equals(stageFileDigest)) {
                    continue;
                }
            }
            logger.debug("Deploy " + stageFile.getPath() + " to " + deployedFile.getPath());
            FileUtils.copyFile(stageFile, deployedFile);
        }
    }

    protected void deployConfig() throws Exception {
        String appserverHomeDirname = getPluginsWrapper().getProperties().getProperty("appserver.home");
        File appserverHomeDir = new File(appserverHomeDirname);
        if (!appserverHomeDir.exists()) {
            throw new RuntimeException("appserver home does not exist at: " + appserverHomeDir.getPath());
        }
        File appserverStageDir = new File(getStageDir(), appserverHomeDir.getName());
        if (!appserverStageDir.exists()) {
            appserverStageDir.mkdirs();
        }
        Collection<File> stageFiles = FileUtils.listFiles(appserverStageDir, null, true);
        for (File stageFile : stageFiles) {
            String relativeStageFilename = stageFile.getPath().substring(appserverStageDir.getPath().length());
            File deployedFile = new File(appserverHomeDir, relativeStageFilename);
            if (deployedFile.exists()) {
                String stageFileDigest = TolvenMessageDigest.checksum(stageFile.toURI().toURL(), MESSAGE_DIGEST_ALGORITHM);
                String deployedFileDigest = TolvenMessageDigest.checksum(deployedFile.toURI().toURL(), MESSAGE_DIGEST_ALGORITHM);
                if (deployedFileDigest.equals(stageFileDigest)) {
                    continue;
                }
            }
            logger.info("Deploy " + stageFile.getPath() + " to " + deployedFile.getPath());
            FileUtils.copyFile(stageFile, deployedFile);
        }
    }

    protected void deployEarFiles(String[] earPlugins, File appserverHomeDir) throws Exception {
        Collection<File> filesToDeploy = null;
        for (String earPlugin : earPlugins) {
            File earDir = new File(getStageDir(), earPlugin);
            if (!earDir.exists()) {
                throw new RuntimeException(earDir.getPath() + " does not exist");
            }
            filesToDeploy = FileUtils.listFiles(earDir, null, true);
            deploy(filesToDeploy, earDir, appserverHomeDir);
        }
    }

    protected void deployRarFiles(String[] rarPlugins, File appserverHomeDir) throws Exception {
        Collection<File> filesToDeploy = null;
        for (String rarPlugin : rarPlugins) {
            File rarDir = new File(getStageDir(), rarPlugin);
            if (!rarDir.exists()) {
                throw new RuntimeException(rarDir.getPath() + " does not exist");
            }
            filesToDeploy = FileUtils.listFiles(rarDir, null, true);
            deploy(filesToDeploy, rarDir, appserverHomeDir);
        }
    }

    @Override
    protected void doStart() throws Exception {
        logger.debug("*** start ***");
    }

    @Override
    protected void doStop() throws Exception {
        logger.debug("*** stop ***");
    }

    private Properties escape(Properties jndiProperties) {
        Properties escapedProperties = new Properties();
        List<String> keys = new ArrayList<String>(jndiProperties.stringPropertyNames());
        Collections.sort(keys);
        for (String key : keys) {
            String value = jndiProperties.getProperty(key);
            if (value == null) {
                throw new RuntimeException("null value for JNDI property: " + key);
            }
            String escapedValue = escape(value);
            escapedProperties.setProperty(key, escapedValue);
        }
        return escapedProperties;
    }

    private String escape(String string) {
        return string.replaceAll("=", Matcher.quoteReplacement("\\=")).replaceAll(":", Matcher.quoteReplacement("\\:"));
    }

    @Override
    public void execute(String[] args) throws Exception {
        logger.debug("*** execute ***");
        CommandLine commandLine = getCommandLine(args);
        String appserverHomeDirname = getPluginsWrapper().getProperties().getProperty("appserver.home");
        File appserverHomeDir = new File(appserverHomeDirname);
        if (!appserverHomeDir.exists()) {
            throw new RuntimeException("appserver home does not exist at: " + appserverHomeDir.getPath());
        }
        boolean deployConfigFiles = commandLine.hasOption(CMD_LINE_CONFIG_OPTION);
        if (deployConfigFiles) {
            deployConfig();
        }
        String earPluginIds = commandLine.getOptionValue(CMD_LINE_EAR_PLUGINS_OPTION);
        if (earPluginIds != null) {
            deployEarFiles(earPluginIds.split(","), appserverHomeDir);
        }
        String rarPluginIds = commandLine.getOptionValue(CMD_LINE_RAR_PLUGINS_OPTION);
        if (rarPluginIds != null) {
            deployRarFiles(rarPluginIds.split(","), appserverHomeDir);
        }
        if (commandLine.hasOption(CMD_JNDI)) {
            Properties srcProperties = getPluginsWrapper().getProperties();
            assembleJNDI(srcProperties);
        }
    }

    private String getASAdminPropertyString(Properties properties) {
        List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
        Iterator<String> it = keys.iterator();
        StringBuffer buff = new StringBuffer();
        while (it.hasNext()) {
            String key = it.next();
            String value = properties.getProperty(key);
            buff.append(key);
            buff.append("=");
            buff.append(value);
            if (it.hasNext()) {
                buff.append(":");
            }
        }
        return buff.toString();
    }

    private CommandLine getCommandLine(String[] args) {
        GnuParser parser = new GnuParser();
        try {
            return parser.parse(getCommandOptions(), args, true);
        } catch (ParseException ex) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getName(), getCommandOptions());
            throw new RuntimeException("Could not parse command line for: " + getClass().getName(), ex);
        }
    }

    private Options getCommandOptions() {
        Options cmdLineOptions = new Options();
        Option rarPluginsOption = new Option(CMD_LINE_RAR_PLUGINS_OPTION, CMD_LINE_RAR_PLUGINS_OPTION, true, "comma-separated list of rar plugins");
        cmdLineOptions.addOption(rarPluginsOption);
        Option earPluginsOption = new Option(CMD_LINE_EAR_PLUGINS_OPTION, CMD_LINE_EAR_PLUGINS_OPTION, true, "comma-separated list of ear plugins");
        cmdLineOptions.addOption(earPluginsOption);
        Option configOption = new Option(CMD_LINE_CONFIG_OPTION, CMD_LINE_CONFIG_OPTION, false, "glassfish configuration files");
        cmdLineOptions.addOption(configOption);
        Option installBuildDirOption = new Option(CMD_JNDI, CMD_JNDI, false, "\"" + CMD_JNDI + " deploy jndi properties\"");
        cmdLineOptions.addOption(installBuildDirOption);
        return cmdLineOptions;
    }

}
