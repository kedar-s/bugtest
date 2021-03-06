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
package org.tolven.gatekeeper.filter;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.tolven.naming.TolvenContext;
import org.tolven.naming.WebContext;

/**
 * This filter requires the user to have already been authenticated before passing through it
 * 
 * @author Joseph Isaac
 *
 */
public class RSAuthorizationFilter extends AuthorizationFilter {

    private boolean initialized = false;

    public RSAuthorizationFilter() {
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (!initialized) {
            try {
                InitialContext ictx = new InitialContext();
                TolvenContext tolvenContext = (TolvenContext) ictx.lookup("tolvenContext");
                String webContextId = (String) ictx.lookup("java:comp/env/webContextId");
                WebContext webContext = (WebContext) tolvenContext.getWebContext(webContextId);
                String loginPath = webContext.getRsLoginPath();
                if (loginPath == null) {
                    throw new RuntimeException("No WebContext loginPath found for contextPath: " + webContextId);
                }
                setLoginUrl(loginPath);
            } catch (Exception ex) {
                throw new RuntimeException("Could not get loginUrl", ex);
            }
        }
        HttpServletRequest req = (HttpServletRequest) request;
        if ("GET".equals(req.getMethod())) {
            request.setAttribute("org.tolven.request.attribute.readonlytransaction", "true");
        }
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        if (isLoginRequest(servletRequest, servletResponse)) {
            return true;
        } else {
            Subject subject = getSubject(servletRequest, servletResponse);
            return subject.isAuthenticated();
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
