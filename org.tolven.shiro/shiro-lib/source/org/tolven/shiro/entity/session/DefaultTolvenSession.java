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
package org.tolven.shiro.entity.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.tolven.session.TolvenSession;
import org.tolven.session.TolvenSessionThreadContext;

@Entity
public class DefaultTolvenSession implements TolvenSession, ValidatingSession, Serializable {

    public static final String TRANSIENT_ATTRIBUTES = "org.tolven.session.transientAttributes";

    @Transient
    private static AesCipherService aes;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DefaultTolvenSessionAttribute> attributes;

    private boolean expired;

    private String host;

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date stopTimestamp;

    private long timeout;

    @Transient
    private Set<String> transientAttributeNames;

    @Transient
    private Map<Object, Object> transientAttributes;

    public DefaultTolvenSession() {
        this.timeout = DefaultSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT; //TODO - remove concrete reference to DefaultSessionManager
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
    }

    private byte[] decodeDecrypt(String encodedEncyptedData) {
        byte[] encryptedData = Base64.decode(encodedEncyptedData);
        byte[] data = getAesCipherService().decrypt(encryptedData, getSecretKey()).getBytes();
        return data;
    }

    private Object deserializeAttribute(String name, byte[] serializedAttribute) {
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedAttribute);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception ex) {
            throw new RuntimeException("Could not deserialize attribute: " + name, ex);
        }
    }

    private String encryptEncode(byte[] data) {
        byte[] encryptedAttributes = getAesCipherService().encrypt(data, getSecretKey()).getBytes();
        String encodedEncryptedAttributes = Base64.encodeToString(encryptedAttributes);
        return encodedEncryptedAttributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultTolvenSession))
            return false;
        DefaultTolvenSession other = (DefaultTolvenSession) obj;
        return other.getId().equals(getId());
    }

    protected void expire() {
        stop();
        this.expired = true;
    }

    private AesCipherService getAesCipherService() {
        if (aes == null) {
            aes = new AesCipherService();
        }
        return aes;
    }

    @Override
    public Object getAttribute(Object name) {
        if (isTransientAttribute(name)) {
            return getTransientAttribute(name);
        } else {
            Set<DefaultTolvenSessionAttribute> attributes = getAttributes();
            if (attributes == null) {
                return null;
            }
            for (DefaultTolvenSessionAttribute attr : attributes) {
                if (attr.getName().equals(name)) {
                    String encodedEncryptedAttribute = attr.getValue();
                    byte[] serializedAttribute = decodeDecrypt(encodedEncryptedAttribute);
                    return deserializeAttribute(attr.getName(), serializedAttribute);
                }
            }
            return null;
        }
    }

    @Override
    public Collection<Object> getAttributeKeys() {
        Set<DefaultTolvenSessionAttribute> attributes = getAttributes();
        Set<Object> keys = new HashSet<Object>();
        keys.addAll(getTransientAttributes().keySet());
        if (attributes != null) {
            for (DefaultTolvenSessionAttribute attr : attributes) {
                keys.add(attr.getName());
            }
        }
        return keys;
    }

    public Set<DefaultTolvenSessionAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new HashSet<DefaultTolvenSessionAttribute>();
        }
        return attributes;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    private byte[] getSecretKey() {
        byte[] secretKey = TolvenSessionThreadContext.getInstance().getSecretKey();
        if (secretKey == null) {
            throw new RuntimeException("Found no session secret key");
        }
        return secretKey;
    }

    @Override
    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public Date getStopTimestamp() {
        return stopTimestamp;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    private Object getTransientAttribute(Object name) {
        return getTransientAttributes().get(name);
    }

    private Set<String> getTransientAttributeNames() {
        if (transientAttributeNames == null) {
            transientAttributeNames = new HashSet<String>();
            String transientAttributeNamesValue = (String) getAttribute(TRANSIENT_ATTRIBUTES);
            if (transientAttributeNamesValue != null) {
                transientAttributeNames.addAll(Arrays.asList(transientAttributeNamesValue.split(",")));
            }
        }
        return transientAttributeNames;
    }

    private Map<Object, Object> getTransientAttributes() {
        if (transientAttributes == null) {
            transientAttributes = new HashMap<Object, Object>();
        }
        return transientAttributes;
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public boolean isExpired() {
        return expired;
    }

    protected boolean isStopped() {
        return getStopTimestamp() != null;
    }

    protected boolean isTimedOut() {
        if (isExpired()) {
            return true;
        }
        long timeout = getTimeout();
        if (timeout >= 0l) {
            Date lastAccessTime = getLastAccessTime();
            if (lastAccessTime == null) {
                String msg = "session.lastAccessTime for session with id [" + getId() + "] is null.  This value must be set at " + "least once, preferably at least upon instantiation.  Please check the " + getClass().getName() + " implementation and ensure " + "this value will be set (perhaps in the constructor?)";
                throw new IllegalStateException(msg);
            }
            long expireTimeMillis = System.currentTimeMillis() - timeout;
            Date expireTime = new Date(expireTimeMillis);
            return lastAccessTime.before(expireTime);
        }
        return false;
    }

    private boolean isTransientAttribute(Object name) {
        for (String transientAttributeName : getTransientAttributeNames()) {
            if (((String) name).matches(transientAttributeName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValid() {
        return !isStopped() && !isExpired();
    }

    @Override
    public void logout() {
        stop();
    }

    @Override
    public Object removeAttribute(Object name) {
        Set<DefaultTolvenSessionAttribute> attributes = getAttributes();
        if (attributes == null) {
            return null;
        } else {
            Iterator<DefaultTolvenSessionAttribute> it = attributes.iterator();
            while (it.hasNext()) {
                DefaultTolvenSessionAttribute attr = it.next();
                if (attr.getName().equals(name)) {
                    it.remove();
                    String encodedEncryptedAttribute = attr.getValue();
                    byte[] serializedAttribute = decodeDecrypt(encodedEncryptedAttribute);
                    return deserializeAttribute(attr.getName(), serializedAttribute);
                }
            }
            return null;
        }
    }

    private Object removeTransientAttribute(Object name) {
        return getTransientAttributes().remove(name);
    }

    private byte[] serializeAttribute(Object name, Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] serializedBytes = baos.toByteArray();
            return serializedBytes;
        } catch (Exception ex) {
            throw new RuntimeException("Could not serialize attribute: " + name, ex);
        }
    }

    @Override
    public void setAttribute(Object name, Object value) {
        if (isTransientAttribute(name)) {
            setTransientAttribute(name, value);
        } else {
            if (value == null) {
                removeAttribute(name);
            } else {
                byte[] serializedAttribute = serializeAttribute(name, value);
                String encodedEncryptedAttribute = encryptEncode(serializedAttribute);
                for (DefaultTolvenSessionAttribute attr : getAttributes()) {
                    if (attr.getName().equals(name)) {
                        attr.setValue(encodedEncryptedAttribute);
                        return;
                    }
                }
                DefaultTolvenSessionAttribute attr = new DefaultTolvenSessionAttribute();
                attr.setSession(this);
                attr.setName((String) name);
                attr.setValue(encodedEncryptedAttribute);
                attr.setEncrypted(true);
                getAttributes().add(attr);
            }
        }
    }

    public void setAttributes(Set<DefaultTolvenSessionAttribute> attributes) {
        this.attributes = attributes;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setStopTimestamp(Date stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    private void setTransientAttribute(Object name, Object value) {
        if (value == null) {
            removeTransientAttribute(name);
        } else {
            getTransientAttributes().put(name, value);
        }
    }

    public void setTransientAttributeNames(Set<String> transientAttributeNames) {
        this.transientAttributeNames = transientAttributeNames;
    }

    public void setTransientAttributes(Map<Object, Object> transientAttributes) {
        this.transientAttributes = transientAttributes;
    }

    @Override
    public void stop() {
        if (this.stopTimestamp == null) {
            this.stopTimestamp = new Date();
        }
    }

    @Override
    public void touch() {
        this.lastAccessTime = new Date();
    }

    @Override
    public void validate() throws InvalidSessionException {
        //check for stopped:
        if (isStopped()) {
            //timestamp is set, so the session is considered stopped:
            String msg = "Session with id [" + getId() + "] has been " + "explicitly stopped.  No further interaction under this session is " + "allowed.";
            throw new StoppedSessionException(msg);
        }

        //check for expiration
        if (isTimedOut()) {
            expire();

            //throw an exception explaining details of why it expired:
            Date lastAccessTime = getLastAccessTime();
            long timeout = getTimeout();

            Serializable sessionId = getId();

            DateFormat df = DateFormat.getInstance();
            String msg = "Session with id [" + sessionId + "] has expired. " + "Last access time: " + df.format(lastAccessTime) + ".  Current time: " + df.format(new Date()) + ".  Session timeout is set to " + timeout / 1000 + " seconds (" + timeout / (60 * 1000) + " minutes)";
            throw new ExpiredSessionException(msg);
        }
    }

}
