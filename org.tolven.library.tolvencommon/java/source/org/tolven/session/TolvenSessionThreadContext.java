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
 */
package org.tolven.session;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.tolven.security.cert.KeyHelper;

public class TolvenSessionThreadContext {

    private static final ThreadLocal<TolvenSessionThreadContext> instance = new ThreadLocal<TolvenSessionThreadContext>();

    public static TolvenSessionThreadContext getInstance() {
        if (instance.get() == null) {
            instance.set(new TolvenSessionThreadContext());
        }
        return instance.get();
    }

    public static String getSessionId(String extendedSessionId) {
        if (extendedSessionId == null || extendedSessionId.trim().length() == 0) {
            return null;
        } else {
            int index = extendedSessionId.indexOf("_");
            if (index == -1) {
                return extendedSessionId;
            } else {
                return extendedSessionId.substring(0, index);
            }
        }
    }

    private Logger logger = Logger.getLogger(TolvenSessionThreadContext.class);

    private byte[] secretKey;
    private TolvenSessionWrapper tolvenSessionWrapper;

    public void clear() {
        instance.set(null);
    }

    public String getExtendedSessionId(String sessionId) {
        if (sessionId == null) {
            throw new RuntimeException("sessionId for extended session Ids cannot be null");
        } else if (getSecretKey() == null) {
            throw new RuntimeException("Instance not initialized with secret key");
        } else {
            String urlEncodedSecretKey = null;
            try {
                String encodedSecretKey = new String(Base64.encodeBase64(getSecretKey()), "UTF-8");
                urlEncodedSecretKey = URLEncoder.encode(encodedSecretKey, "UTF-8");
            } catch (Exception ex) {
                throw new RuntimeException("Could not encode secret key", ex);
            }
            return sessionId + "_" + urlEncodedSecretKey;
        }
    }

    public byte[] getSecretKey() {
        return secretKey;
    }

    public TolvenSessionWrapper getTolvenSessionWrapper() {
        return tolvenSessionWrapper;
    }

    public void bind(TolvenSessionWrapper tolvenSessionWrapper, String extendedSessionId) {
        byte[] secretKey = null;
        if (extendedSessionId == null) {
            secretKey = KeyHelper.generateSessionSecretKey().getEncoded();
            if (logger.isDebugEnabled()) {
                logger.debug("Generated session secret key");
            }
        } else {
            int index = extendedSessionId.indexOf("_");
            if (index == -1) {
                throw new RuntimeException("Unrecognized extendedSessionId format");
            } else {
                try {
                    String urlDecodedSecretKey = URLDecoder.decode(extendedSessionId.substring(1 + extendedSessionId.indexOf("_")), "UTF-8");
                    secretKey = Base64.decodeBase64(urlDecodedSecretKey.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException("Could not base64 decode the extended session Id", ex);
                }
            }
        }
        setSecretKey(secretKey);
        setSessionWrapper(tolvenSessionWrapper);
    }

    private void setSecretKey(byte[] secretKey) {
        if (this.secretKey != null) {
            throw new RuntimeException("SecretKey already initialized");
        }
        this.secretKey = secretKey;
    }

    private void setSessionWrapper(TolvenSessionWrapper tolvenSessionWrapper) {
        if (this.tolvenSessionWrapper != null) {
            throw new RuntimeException("TolvenSessionWrapper already initialized");
        }
        this.tolvenSessionWrapper = tolvenSessionWrapper;
    }

}
