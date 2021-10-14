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
package org.tolven.shiro.web.session.mgt;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.tolven.naming.TolvenContext;
import org.tolven.session.TolvenSessionThreadContext;
import org.tolven.shiro.session.DefaultTolvenSessionFactory;
import org.tolven.shiro.session.LastAccessTimeLocal;
import org.tolven.shiro.session.TolvenSessionDAO;

public class TolvenWebSessionManager extends DefaultWebSessionManager {

    public static final String TRANSIENT_ATTRIBUTES = "org.tolven.session.transientAttributes";

    @EJB
    private LastAccessTimeLocal lastAccessTimeBean;

    private Logger logger = Logger.getLogger(TolvenWebSessionManager.class);

    public TolvenWebSessionManager() {
        updateSessionIdTemplateCookie();
        setSessionFactory(new DefaultTolvenSessionFactory());
        setSessionDAO(new TolvenSessionDAO());
    }

    private LastAccessTimeLocal getLastAccessTimeBean() {
        if (lastAccessTimeBean == null) {
            String jndiName = null;
            try {
                InitialContext ctx = new InitialContext();
                jndiName = "java:app/shiro-ejb/LastAccessTimeBean!org.tolven.shiro.session.LastAccessTimeLocal";
                lastAccessTimeBean = (LastAccessTimeLocal) ctx.lookup(jndiName);
            } catch (NamingException ex) {
                throw new RuntimeException("Failed to lookup " + jndiName, ex);
            }
        }
        return lastAccessTimeBean;
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String extendedSessionId = (String) super.getSessionId(request, response);
        return TolvenSessionThreadContext.getSessionId(extendedSessionId);
    }

    @Override
    protected void onStart(Session session, SessionContext context) {
        HttpServletRequest request = WebUtils.getHttpRequest(context);
        String transientAttributes = request.getServletContext().getInitParameter(TRANSIENT_ATTRIBUTES);
        session.setAttribute(TRANSIENT_ATTRIBUTES, transientAttributes);
        onChange(session);
        super.onStart(session, context);
        HttpServletResponse response = WebUtils.getHttpResponse(context);
        //Remove cookie added by super class
        Cookie template = getSessionIdCookie();
        Cookie cookie = new SimpleCookie(template);
        cookie.removeFrom(request, response);
        /*
         * Now place the secret key in a cookie by combining it with the sessionId using a
         * two way algorithm
         */
        if (logger.isDebugEnabled()) {
            logger.debug("Creating secret key cookie for cookie template name: " + template.getName());
        }
        String sessionId = session.getId().toString();
        cookie.setValue(TolvenSessionThreadContext.getInstance().getExtendedSessionId(sessionId));
        cookie.saveTo(request, response);
        if (logger.isDebugEnabled()) {
            logger.debug("Saved secret key cookie to response for session: " + sessionId);
        }
    }

    public void touch(SessionKey key) throws InvalidSessionException {
        if (key == null) {
            throw new NullPointerException("SessionKey argument cannot be null.");
        }
        Session session = doGetSession(key);
        if (session == null) {
            String msg = "Unable to locate required Session instance based on SessionKey [" + key + "].";
            throw new UnknownSessionException(msg);
        }
        getLastAccessTimeBean().addSessionId((String) session.getId());
    }

    protected void updateSessionIdTemplateCookie() {
        Cookie cookie = getSessionIdCookie();
        TolvenContext tolvenContext = null;
        String jndiName = "tolvenContext";
        try {
            InitialContext ictx = new InitialContext();
            tolvenContext = (TolvenContext) ictx.lookup(jndiName);
        } catch (Exception ex) {
            throw new RuntimeException("Could not look up " + jndiName, ex);
        }
        if (tolvenContext.getSsoCookieName() != null) {
            cookie.setName(tolvenContext.getSsoCookieName());
        }
        if (tolvenContext.getSsoCookieDomain() != null) {
            cookie.setDomain(tolvenContext.getSsoCookieDomain());
        }
        if (tolvenContext.getSsoCookiePath() != null) {
            cookie.setPath(tolvenContext.getSsoCookiePath());
        }
        if (tolvenContext.getSsoCookieSecure() != null) {
            cookie.setSecure(Boolean.parseBoolean(tolvenContext.getSsoCookieSecure()));
        }
        if (tolvenContext.getSsoCookieMaxAge() != null) {
            cookie.setMaxAge(Integer.parseInt(tolvenContext.getSsoCookieMaxAge()));
        }
    }

}
