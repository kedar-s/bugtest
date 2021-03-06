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
 * @version $Id$
 */
package org.tolven.client.examples.ws.common;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author Joseph Isaac
 */
public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

    public static String WSSE11_NS = "http://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd";
    public static String WS_ADDRESSING = "http://www.w3.org/2005/08/addressing";
    public static String WS_SECUTILITY = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
    public static String WS_USER_TOKEN_PROFILE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText";

    private String username;
    private char[] password;
    private int expiresInSeconds;

    public HeaderHandler(String username, char[] password, int expiresInSeconds) {
        setUsername(username);
        setPassword(password);
        setExpiresInSeconds(expiresInSeconds);
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private char[] getPassword() {
        return password;
    }

    private void setPassword(char[] password) {
        this.password = password;
    }

    private int getExpiresInSeconds() {
        return expiresInSeconds;
    }

    private void setExpiresInSeconds(int expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            try {
                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
                SOAPHeader header = null;
                if (envelope.getHeader() == null) {
                    header = envelope.addHeader();
                } else {
                    header = envelope.getHeader();
                }
                SOAPElement messageID = header.addChildElement("MessageID", "wsa", WS_ADDRESSING);
                messageID.addTextNode(UUID.randomUUID().toString());
                SOAPElement security = header.addChildElement("Security", "wsse", WSSE11_NS);
                security.addAttribute(new QName("wsse:mustUnderstand"), "1");
                SOAPElement timestamp = security.addChildElement("Timestamp", "wsu", WS_SECUTILITY);
                DatatypeFactory factory = DatatypeFactory.newInstance();
                SOAPElement createdElement = timestamp.addChildElement("Created", "wsu");
                GregorianCalendar gCal = new GregorianCalendar();
                String xmlNow = factory.newXMLGregorianCalendar(gCal).toXMLFormat();
                createdElement.addTextNode(xmlNow);
                SOAPElement expiresElement = timestamp.addChildElement("Expires", "wsu");
                gCal.add(GregorianCalendar.SECOND, getExpiresInSeconds());
                String xmlExpires = factory.newXMLGregorianCalendar(gCal).toXMLFormat();
                expiresElement.addTextNode(xmlExpires);
                SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
                //Following usernameToken attribute should work with contained attributeless <Created> tag, but parser fails
                //usernameToken.addAttribute(new QName("xmlns:wsu"), WS_SECUTILITY);
                SOAPElement username = usernameToken.addChildElement("Username", "wsse");
                username.addTextNode(getUsername());
                SOAPElement password = usernameToken.addChildElement("Password", "wsse");
                password.setAttribute("Type", WS_USER_TOKEN_PROFILE);
                password.addTextNode(new String(getPassword()));
                SOAPElement createdUser = usernameToken.addChildElement("Created", "wsu", WS_SECUTILITY);
                createdUser.addTextNode(xmlNow);
                //smc.getMessage().writeTo(System.out);
                //System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                //smc.getMessage().writeTo(System.out);
                //System.out.println();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return outboundProperty;

    }

    public Set<QName> getHeaders() {
        QName securityHeader = new QName(WSSE11_NS, "Security", "wsse");
        HashSet<QName> headers = new HashSet<QName>();
        headers.add(securityHeader);
        return headers;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
        return;
    }

}