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
 * @version $Id: TolvenSecurityManager.java 5891 2012-02-14 06:33:24Z joe.isaac $
 */
package org.tolven.shiro.mgt;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.tolven.shiro.authc.UsernamePasswordRealmToken;
import org.tolven.shiro.session.mgt.TolvenSessionManager;

public class TolvenSecurityManager extends DefaultSecurityManager {

    private Logger logger = Logger.getLogger(TolvenSecurityManager.class);
    
    public TolvenSecurityManager() {
        setSessionManager(new TolvenSessionManager());
        setRememberMeManager(null);
    }

    @Override
    protected void onFailedLogin(AuthenticationToken token, AuthenticationException ae, Subject subject) {
        String realm = ((UsernamePasswordRealmToken) token).getRealm();
        logger.info("User: " + token.getPrincipal() + " realm: " + realm + " FAILED_LOGIN");
        super.onFailedLogin(token, ae, subject);
    }

    @Override
    protected void onSuccessfulLogin(AuthenticationToken token, AuthenticationInfo info, Subject subject) {
        String realm = ((UsernamePasswordRealmToken) token).getRealm();
        logger.info("User: " + token.getPrincipal() + " realm: " + realm + " SUCCESSFUL_LOGIN");
        super.onSuccessfulLogin(token, info, subject);
    }

}
