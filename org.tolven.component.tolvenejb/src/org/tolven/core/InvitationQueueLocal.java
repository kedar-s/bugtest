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
package org.tolven.core;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.List;

public interface InvitationQueueLocal {

    public String getQueueName();
    
    public X509Certificate getQueueOwnerX509Certificate();

    /**
     * Queue a list of payloads.
     * One connection is used to send the List of payloads
     * @param payloads
     */
    public void send(List<Serializable> payloads);

    /**
     * Queue a payload.
     * @param payload
     */
    public void send(Serializable payload);

}
