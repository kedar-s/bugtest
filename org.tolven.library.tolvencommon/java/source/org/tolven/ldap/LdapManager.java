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
 * @version $Id: LdapManager.java 7332 2012-12-02 06:41:26Z joe.isaac $
 */
package org.tolven.ldap;

import java.util.Set;

import javax.naming.directory.Attributes;

public interface LdapManager {

    /**
     * Change password of user given by getUserDN()
     * 
     * @param newPassword
     */
    public void changePassword(char[] newPassword);

    /**
     * User given by getUserDN() changes password of the user given by parameter userDN
     * 
     * @param userDN
     * @param oldPassword
     * @param newPassword
     */
    public void changePassword(String userDN, char[] oldPassword, char[] newPassword);

    /**
     * Check password of user given by getUserDN()
     * 
     */
    public void checkPassword();
    
    /**
     * User given by getUserDN() checks password of the user given by parameter userDN
     * 
     * @param userDN
     * @param password
     */
    public void checkPassword(String userDN, char[] password);

    /**
     * User given by getUserDN() creates user given by parameter userDN and userAttributes
     * 
     * @param userDN
     * @param userPassword
     * @param userAttributes
     * @return
     */
    public char[] createUser(String userDN, char[] userPassword, Attributes userAttributes);

    /**
     * Disconnect from LDAP
     */
    public void disconnect();

    /**
     * Find user userDN
     * 
     * @param userDN
     * @return
     */
    public Attributes findUser(String userDN);

    /**
     * Generate new credentials for user who is logged in, but only if they had none
     */
    public boolean generateNewUserPKCS12IfNone();

    /**
     * User given by getUserDN() gets attributes of user given by parameter userDN
     * 
     * @param userDN
     * @param attrIds
     * @return
     */
    public Attributes getAttributes(String userDN, String[] attrIds);

    /**
     * Get attributes of user given by getUserDN()
     * 
     * @param attrIds
     * @return
     */
    public Attributes getAttributes(String[] attrIds);

    /**
     * Determine whether the password of user given by getUserDN() is expiring.
     * null means it is not, otherwise the remaining time can be determined from the returned PasswordExpiring
     * 
     * @return
     */
    public PasswordExpiring getPasswordExpiring();

    /**
     * Return the realm for this LDAP
     * 
     * @return
     */
    public String getRealm();

    /**
     * Get roles of user given by getUserDN()
     * 
     * @return
     */
    public Set<String> getRoles();

    /**
     * User given by getUserDN() gets roles of user given by parameter userDN
     * 
     * @param userDN
     * @return
     */
    public Set<String> getRoles(String userDN);

    /**
     * Return the userDN which is connected to LDAP by this instance
     * 
     * @return
     */
    public String getUserDN();

    /**
     * Determine whether the password of user given by getUserDN() is expired.
     * 
     * @return
     */
    public boolean isPasswordExpired();
    
    /**
     * User given by getUserDN() resets the password of user given by parameter userDN
     * 
     * @param userDN
     * @return
     */
    public char[] resetPassword(String userDN);
    
}
