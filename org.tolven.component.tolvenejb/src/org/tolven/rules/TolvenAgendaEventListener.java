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
 * @version $Id: TolvenAgendaEventListener.java 6483 2012-05-16 00:15:33Z joe.isaac $
 */
package org.tolven.rules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.drools.FactHandle;
import org.drools.WorkingMemory;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.AgendaEventListener;
import org.drools.event.AgendaGroupPoppedEvent;
import org.drools.event.AgendaGroupPushedEvent;
import org.drools.event.BeforeActivationFiredEvent;
import org.drools.rule.Declaration;
import org.drools.spi.Activation;
import org.drools.spi.Tuple;

public class TolvenAgendaEventListener implements AgendaEventListener {

    public static Logger logger = Logger.getLogger(TolvenAgendaEventListener.class);

    private Map<String, Long> ruleTimestamp = new HashMap<String, Long>();

    @Override
    public void activationCancelled(ActivationCancelledEvent event, WorkingMemory workingMemory) {
    }

    @Override
    public void activationCreated(ActivationCreatedEvent event, WorkingMemory workingMemory) {
    }

    @Override
    public void afterActivationFired(AfterActivationFiredEvent event, WorkingMemory workingMemory) {
        Long end = System.currentTimeMillis();
        Activation activation = event.getActivation();
        String ruleName = activation.getRule().getName();
        Long start = ruleTimestamp.remove(ruleName);
        if (start == null) {
            throw new RuntimeException("Start timestamp not set up for rule: " + ruleName);
        }
        logger.debug("fire_rule," + String.valueOf(end - start) + ",'" + ruleName + "'," + extractDeclarations(activation, workingMemory));
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event, WorkingMemory workingMemory) {
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event, WorkingMemory workingMemory) {
    }

    @Override
    public void beforeActivationFired(BeforeActivationFiredEvent event, WorkingMemory workingMemory) {
        String ruleName = event.getActivation().getRule().getName();
        ruleTimestamp.put(ruleName, System.currentTimeMillis());
    }

    private String extractDeclarations(final Activation activation, final WorkingMemory workingMemory) {
        final StringBuffer result = new StringBuffer();
        final Tuple tuple = activation.getTuple();
        final Map declarations = activation.getSubRule().getOuterDeclarations();
        for (Iterator<Declaration> it = declarations.values().iterator(); it.hasNext();) {
            final Declaration declaration = it.next();
            final FactHandle handle = tuple.get(declaration);
            if (handle instanceof InternalFactHandle) {
                final InternalFactHandle handleImpl = (InternalFactHandle) handle;
                if (handleImpl.getId() == -1) {
                    // This handle is now invalid, probably due to an fact retraction
                    continue;
                }
                final Object value = declaration.getValue((InternalWorkingMemory) workingMemory, handleImpl.getObject());

                result.append(declaration.getIdentifier());
                result.append("=");
                if (value == null) {
                    // this should never occur
                    result.append("null");
                } else {
                    result.append(value);
                    result.append("(");
                    result.append(handleImpl.getId());
                    result.append(")");
                }
            }
            if (it.hasNext()) {
                result.append("; ");
            }
        }
        return result.toString();
    }

}
