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
 * @version $Id: LastAccessTimeBean.java 6666 2012-06-14 20:38:43Z joe.isaac $
 */
package org.tolven.shiro.session.bean;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.tolven.shiro.api.session.TolvenSessionLocal;
import org.tolven.shiro.entity.session.DefaultTolvenSession;
import org.tolven.shiro.session.LastAccessTimeLocal;

@Startup
@Singleton
@Local(LastAccessTimeLocal.class)
public class LastAccessTimeBean implements LastAccessTimeLocal {

    private static Set<String> lastAccessTimeCache = new HashSet<String>();
    private static String UPDATE_PERIOD_PROP = "tolven.shiro.lastAccessTime.updatePeriod";
    private static int DEFAULT_UPDATE_PERIOD = 300;

    @Resource
    private EJBContext ejbContext;

    private Logger logger = Logger.getLogger(LastAccessTimeBean.class);

    @EJB
    private TolvenSessionLocal tolvenSessionBean;

    public void addSessionId(String sessionId) {
        lastAccessTimeCache.add(sessionId);
        updateTimers();
    }

    private Set<String> clearCache() {
        Set<String> lastAccessTimeCacheCopy = new HashSet<String>();
        lastAccessTimeCacheCopy.addAll(lastAccessTimeCache);
        if (logger.isDebugEnabled()) {
            logger.debug("Clearing lastAccessTimeCache of objects: " + lastAccessTimeCache.size());
        }
        lastAccessTimeCache.clear();
        return lastAccessTimeCacheCopy;
    }

    private void createTimer(long updatePeriod) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(String.valueOf(updatePeriod));
        ejbContext.getTimerService().createIntervalTimer(0, updatePeriod, timerConfig);
    }

    @Timeout
    protected void updateLastAccessTimes() {
        Set<String> lastAccessTimeCacheCopy = clearCache();
        for (String sessionId : lastAccessTimeCacheCopy) {
            try {
                // The following condition is added due to JBoss bug: EJBTHREE-2248
                if (tolvenSessionBean == null) {
                    String jndiName = "java:app/shiro-ejb/TolvenSessionBean!org.tolven.shiro.api.session.TolvenSessionLocal";
                    InitialContext ctx = new InitialContext();
                    tolvenSessionBean = (TolvenSessionLocal) ctx.lookup(jndiName);
                }
                DefaultTolvenSession session = tolvenSessionBean.findSession(sessionId);
                if (session != null && session.isValid()) {
                    session.setLastAccessTime(new Date());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                // move onto next session
            }
        }
    }

    private void updateTimers() {
        //TODO This should be using a class similar to the propertyBean
        String systemUpdatePeriodString = System.getProperty(UPDATE_PERIOD_PROP);
        long currentUpdatePeriod = DEFAULT_UPDATE_PERIOD * 1000;
        if (systemUpdatePeriodString != null) {
            try {
                currentUpdatePeriod = Long.parseLong(systemUpdatePeriodString) * 1000;
            } catch (Exception ex) {
                logger.debug("Could not parse System property: " + UPDATE_PERIOD_PROP, ex);
                // using default time
            }
        }
        // The following condition is added due to JBoss bug: EJBTHREE-2248
        if (ejbContext == null) {
            try {
                ejbContext = (EJBContext) new InitialContext().lookup("java:comp/EJBContext");
            } catch (Exception ex) {
                throw new RuntimeException("Could not lookup EJBContext", ex);
            }
        }
        Collection<Timer> timers = ejbContext.getTimerService().getTimers();
        if (timers.size() == 0) {
            createTimer(currentUpdatePeriod);
        } else {
            Timer timer = timers.iterator().next();
            String timerUpdatePeriodString = (String) timer.getInfo();
            long timerUpdatePeriod = Long.parseLong(timerUpdatePeriodString);
            if (timerUpdatePeriod != currentUpdatePeriod) {
                timer.cancel();
                createTimer(currentUpdatePeriod);
            }
        }
    }

}
