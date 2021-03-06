/*
 *  Copyright (C) 2006 Tolven Inc 
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU Lesser General Public License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 * 
 * Contact: info@tolvenhealth.com
 */
package org.tolven.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.tolven.session.TolvenSessionWrapper;
import org.tolven.session.TolvenSessionWrapperFactory;

/**
 * Intercepts requests to logout users
 * 
 * @author Joseph Isaac
 */
public class SwitchAccountFilter implements Filter {

    private Logger logger = Logger.getLogger(GeneralSecurityFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        TolvenSessionWrapper sessionWrapper = TolvenSessionWrapperFactory.getInstance();
        String principal = (String) sessionWrapper.getPrincipal();
        String realm = (String) sessionWrapper.getRealm();
        String accountId = (String) sessionWrapper.getAttribute(GeneralSecurityFilter.ACCOUNT_ID);
        logger.info("User: " + principal + " realm: " + realm + " SWITCHING_FROM_ACCOUNT " + accountId);
        sessionWrapper.logout();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String loginURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "?" + GeneralSecurityFilter.SWITCH_ACCOUNT + "=true";
        response.sendRedirect(loginURL);
    }

    public void destroy() {
    }

}