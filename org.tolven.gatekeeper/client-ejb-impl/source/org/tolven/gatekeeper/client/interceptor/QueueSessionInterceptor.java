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
package org.tolven.gatekeeper.client.interceptor;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDrivenContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.tolven.gatekeeper.client.api.RSGatekeeperClientLocal;
import org.tolven.naming.QueueContext;
import org.tolven.naming.TolvenContext;
import org.tolven.session.TolvenSessionThreadContext;
import org.tolven.shiro.mgt.TolvenSecurityManager;
import org.tolven.shiro.session.ShiroSessionWrapper;

@Interceptor
public class QueueSessionInterceptor {

    private static final SecurityManager securityManager = new TolvenSecurityManager();
    
    @EJB
    private RSGatekeeperClientLocal rsGatekeeperClientBean;

    @Resource
    private MessageDrivenContext ctx;

    @Resource(name = "queueId")
    private String queueId;

    private static Logger logger = Logger.getLogger(QueueSessionInterceptor.class);

    private String getQueueId() {
        if (queueId == null) {
            //JBoss comes back with null for some reason, and I don't want to use their proprietary deployment descriptor if possible
            //Works fine in Glassfish
            String jndiName = "java:comp/env/queueId";
            try {
                InitialContext ictx = new InitialContext();
                queueId = (String) ictx.lookup(jndiName);
            } catch (Exception ex) {
                throw new RuntimeException("Could not look up " + jndiName, ex);
            }
        }
        return queueId;
    }

    @AroundInvoke
    public Object initializeSession(final InvocationContext invCtx) throws Exception {
        Object result = null;
        Subject subject = null;
        try {
            InitialContext ictx = new InitialContext();
            TolvenContext tolvenContext = (TolvenContext) ictx.lookup("tolvenContext");
            QueueContext queueContext = (QueueContext) tolvenContext.getQueueContext(getQueueId());
            String user = queueContext.getUser();
            char[] password = queueContext.getPassword().toCharArray();
            String realm = queueContext.getRealm();
            String extendedSessionId = rsGatekeeperClientBean.login(user, password, realm, true);
            TolvenSessionThreadContext.getInstance().bind(new ShiroSessionWrapper(), extendedSessionId);
            String sessionId = TolvenSessionThreadContext.getSessionId(extendedSessionId);
            subject = new Subject.Builder(securityManager).sessionId(sessionId).buildSubject();
            ThreadContext.bind(subject);
            return invCtx.proceed();
        } catch (Exception ex) {
            ctx.setRollbackOnly();
            logger.error(ex.getMessage());
            ex.printStackTrace();
            if ("true".equals(System.getProperty("suppressOnMessageException"))) {
                /*
                 * A bug in JBoss causes an infinite loop if an exception is thrown: JBAS-7950
                 * suppressOnMessageException has been placed in the jboss.xml file so that
                 * it does not impact Glassfish, which works correctly 
                 */
            } else {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                rsGatekeeperClientBean.logout();
            } catch (Exception ex) {
                if ("true".equals(System.getProperty("suppressOnMessageException"))) {
                    /*
                     * A bug in JBoss causes an infinite loop if an exception is thrown: JBAS-7950
                     * suppressOnMessageException has been placed in the jboss.xml file so that
                     * it does not impact Glassfish, which works correctly 
                     */
                } else {
                    throw new RuntimeException(ex);
                }
            } finally {
                TolvenSessionThreadContext.getInstance().clear();
            }
        }
        return result;
    }

}
