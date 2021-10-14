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
package org.tolven.gatekeeper.client.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;

import org.tolven.exception.TolvenAuthenticationException;
import org.tolven.exception.TolvenAuthorizationException;
import org.tolven.gatekeeper.client.api.RSGatekeeperClientLocal;
import org.tolven.naming.TolvenContext;
import org.tolven.naming.WebContext;
import org.tolven.session.TolvenSessionThreadContext;
import org.tolven.session.TolvenSessionWrapperFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Stateless
@Local(RSGatekeeperClientLocal.class)
public class RSGatekeeperClientBean implements RSGatekeeperClientLocal {

    private static Client client;
    static {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        client.setFollowRedirects(true);
    }

    private static TolvenContext tolvenContext;

    @Override
    public boolean existsTolvenPerson(String uid, String realm) {
        String principal = (String) TolvenSessionWrapperFactory.getInstance().getPrincipal();
        WebResource webResource = getWebResource("user/" + principal + "/user/" + uid + "/exists");
        ClientResponse response = webResource.queryParam("realm", realm).get(ClientResponse.class);
        String responseString = response.getEntity(String.class);
        if (response.getStatus() == 401) {
            throw new TolvenAuthenticationException(responseString);
        } else if (response.getStatus() == 403) {
            throw new TolvenAuthorizationException(responseString);
        } else if (response.getStatus() != 200) {
            throw new RuntimeException("Error: " + response.getStatus() + " " + uid + " " + webResource.getURI() + " " + responseString);
        }
        return Boolean.parseBoolean(responseString);
    }

    private Client getClient() {
        return client;
    }

    private String getCookie(ClientResponse response) {
        String ssoCookieName = getTolvenContext().getSsoCookieName();
        for (Cookie cookie : response.getCookies()) {
            if (ssoCookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        throw new RuntimeException("Cookie " + ssoCookieName + " not found");
    }

    public List<String> getRealmIds() {
        return getTolvenContext().getRealmIds();
    }

    private WebContext getRsWebContext() {
        return (WebContext) getTolvenContext().getRsGatekeeperWebContext();
    }

    private Cookie getSessionCookie() {
        TolvenContext context = getTolvenContext();
        String sessionId = (String) TolvenSessionWrapperFactory.getInstance().getId();
        Cookie cookie = new Cookie(context.getSsoCookieName(), TolvenSessionThreadContext.getInstance().getExtendedSessionId(sessionId), context.getSsoCookiePath(), context.getSsoCookieDomain());
        return cookie;
    }

    private TolvenContext getTolvenContext() {
        if (tolvenContext == null) {
            String jndiName = "tolvenContext";
            try {
                InitialContext ictx = new InitialContext();
                tolvenContext = (TolvenContext) ictx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not look up " + jndiName, ex);
            }
        }
        return tolvenContext;
    }

    /**
     * Callers of this method are required to have previously authenticated with the gatekeeper
     */
    @Override
    public WebResource getWebResource(String path) {
        /*
         * No new session, so add the existing SSOCookie to the WebResource request
         */
        return getWebResource(path, false);
    }

    private WebResource getWebResource(String path, boolean newSession) {
        WebContext webContext = getRsWebContext();
        WebResource webResource = getClient().resource(webContext.getRsInterface()).path(path);
        if (newSession) {
            /*
             * Current SSOCookie is not added to the WebResource
             */
        } else {
            /*
             * Add the current SSOCookie to the WebResource.
             */
            webResource.addFilter(new ClientFilter() {
                @Override
                public ClientResponse handle(ClientRequest clientRequest) throws ClientHandlerException {
                    Cookie sessionCookie = getSessionCookie();
                    clientRequest.getHeaders().putSingle("Cookie", sessionCookie.getName() + "=" + sessionCookie.getValue());
                    return getNext().handle(clientRequest);
                }
            });
        }
        return webResource;
    }

    /**
     * User RESTful to log into the gatekeeper and return the extended session Id
     * @param username
     * @param password
     * @param realm
     * @return
     */
    @Override
    public String login(String username, char[] password, String realm) {
        return login(username, password, realm, false);
    }

    /**
     * User RESTful to log into the gatekeeper and return the extended session Id
     * When newSession is false, the existing session will be used, otherwise a new one
     * will be created and returned by the gatekeeper.
     */
    @Override
    public String login(String username, char[] password, String realm, boolean newSession) {
        WebContext webContext = getRsWebContext();
        /*
         * Do not attempt to add SSOCookie, because this is a request for a new login
         */
        WebResource webResource = getWebResource(webContext.getRsLoginPath(), newSession);
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("username", username);
        formData.add("password", new String(password));
        formData.add("realm", realm);
        ClientResponse response = webResource.post(ClientResponse.class, formData);
        if (response.getStatus() == 401) {
            throw new TolvenAuthenticationException(response.getEntity(String.class));
        } else if (response.getStatus() == 403) {
            throw new TolvenAuthorizationException(response.getEntity(String.class));
        } else if (response.getStatus() != 200) {
            throw new RuntimeException("Error: " + response.getStatus() + " " + username + " " + webResource.getURI() + " " + response.getEntity(String.class));
        }
        return getCookie(response);
    }

    /**
     * Logout the current Subject
     */
    @Override
    public void logout() {
        TolvenSessionWrapperFactory.getInstance().logout();
    }

    @Override
    public boolean verifyUserPassword(String uid, char[] password, String realm) {
        String principal = (String) TolvenSessionWrapperFactory.getInstance().getPrincipal();
        WebResource webResource = getWebResource("user/" + principal + "/user/" + uid + "/verifyPassword");
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("userId", principal);
        formData.add("uid", uid);
        formData.add("uidPassword", new String(password));
        formData.add("realm", realm);
        ClientResponse response = webResource.post(ClientResponse.class, formData);
        String responseString = response.getEntity(String.class);
        if (response.getStatus() == 401) {
            throw new TolvenAuthenticationException(responseString);
        } else if (response.getStatus() == 403) {
            throw new TolvenAuthorizationException(responseString);
        } else if (response.getStatus() != 200) {
            throw new RuntimeException("Error: " + response.getStatus() + " " + uid + " " + webResource.getURI() + " " + responseString);
        }
        return Boolean.parseBoolean(responseString);
    }

}
