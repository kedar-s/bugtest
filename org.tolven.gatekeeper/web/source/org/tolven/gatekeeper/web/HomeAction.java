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
package org.tolven.gatekeeper.web;

import org.tolven.session.TolvenSessionWrapperFactory;

/**
 * Faces Action for a user's home page
 * 
 * @author Joseph Isaac
 */
public class HomeAction {

    public HomeAction() {
    }

    public boolean isAdmin() {
        return TolvenSessionWrapperFactory.getInstance().hasRole("tolvenAdmin");
    }

    public String getPrincipal() {
        return (String) TolvenSessionWrapperFactory.getInstance().getPrincipal();
    }

}
