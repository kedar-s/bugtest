package org.tolven.client.load;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.tolven.restful.client.RESTfulClient;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;

public class LoadAccountTypes extends RESTfulClient {
    private Map<String, String> appFiles;
    private Logger logger = Logger.getLogger(LoadAccountTypes.class);
    /**
     * Constructor
     * @param configDir
     */
    public LoadAccountTypes(String userId, char[] password, String appRestfulURL, String authRestfulURL) {
        appFiles = new LinkedHashMap<String, String>();
        init(userId, password, appRestfulURL, authRestfulURL);
    }

    /**
     * Load up a file into a string
     * @param filename The filename of the file to read
     * @return A String containing the contents of the file
     */
    public String loadFile(File file) {
        FileReader reader = null;
        StringWriter writer = null;
        try {
            reader = new FileReader(file);
            writer = new StringWriter();
            while (true) {
                int c = reader.read();
                if (c < 0)
                    break;
                writer.write(c);
            }
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error reading file " + file, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Scan a directory for files with application.xml extensions each file found.
     * @param directory in which to look for application.xml files
     */
    public void addDirectory(File dir) {
        File files[] = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addDirectory(file);
            } else {
                String normalPath = file.getPath().replace('\\', '/');
                appFiles.put(normalPath, loadFile(file));
            }
        }
    }

    /**
     * Upload the accumulated list of application files.
     */
    public void uploadApplications(String userId) throws Exception {
        try {
            if (!appFiles.keySet().isEmpty()) {
                WebResource webResource = getAppWebResource().path("loader/loadApplications");
                MultiPart multiPart = new MultiPart(new MediaType("multipart", "mixed"));
                for (String key : appFiles.keySet()) {
                	String xml = appFiles.get(key);
                    BodyPart bodyPart = new BodyPart(xml, MediaType.APPLICATION_XML_TYPE);
                    ContentDisposition cd = ContentDisposition.type("attachment").fileName(key).build();
                    bodyPart.setContentDisposition(cd);
                    multiPart.bodyPart(bodyPart);
                }
                try {
                    ClientResponse response = webResource.type("multipart/mixed").cookie(getTokenCookie()).post(ClientResponse.class, multiPart);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Status: " + response.getStatus() + " " + getUserId() + " POST " + webResource.getURI() + " " + response.getEntity(String.class));
                    }
                } finally {
                    logout();
                }
            }
        } catch (Exception ex) {
            //TolvenLogger.info("Rolling back transaction : upload applications for: " + userId, LoadAccountTypes.class);
            throw new RuntimeException("Error uploading applications for: " + userId, ex);
        }
    }

}
