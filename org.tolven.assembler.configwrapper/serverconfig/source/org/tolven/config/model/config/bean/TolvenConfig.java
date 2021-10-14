//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.07.22 at 03:20:53 PM PDT 
//


package org.tolven.config.model.config.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admin" type="{urn:tolven-org:tolven-config:1.0}AdminDetail"/>
 *         &lt;element name="ldap" type="{urn:tolven-org:tolven-config:1.0}LDAPServerDetail"/>
 *         &lt;element name="db" type="{urn:tolven-org:tolven-config:1.0}DBServerDetail"/>
 *         &lt;element name="appServer" type="{urn:tolven-org:tolven-config:1.0}AppServerDetail"/>
 *         &lt;element name="passwordServer" type="{urn:tolven-org:tolven-config:1.0}PasswordServerDetail"/>
 *         &lt;element name="webServer" type="{urn:tolven-org:tolven-config:1.0}WebServerDetail"/>
 *         &lt;element name="application" type="{urn:tolven-org:tolven-config:1.0}ApplicationDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "admin",
    "ldap",
    "db",
    "appServer",
    "passwordServer",
    "webServer",
    "application"
})
@XmlRootElement(name = "tolven-config")
public class TolvenConfig {

    @XmlElement(required = true)
    protected AdminDetail admin;
    @XmlElement(required = true)
    protected LDAPServerDetail ldap;
    @XmlElement(required = true)
    protected DBServerDetail db;
    @XmlElement(required = true)
    protected AppServerDetail appServer;
    @XmlElement(required = true)
    protected PasswordServerDetail passwordServer;
    @XmlElement(required = true)
    protected WebServerDetail webServer;
    @XmlElement(required = true)
    protected ApplicationDetail application;

    /**
     * Gets the value of the admin property.
     * 
     * @return
     *     possible object is
     *     {@link AdminDetail }
     *     
     */
    public AdminDetail getAdmin() {
        return admin;
    }

    /**
     * Sets the value of the admin property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdminDetail }
     *     
     */
    public void setAdmin(AdminDetail value) {
        this.admin = value;
    }

    /**
     * Gets the value of the ldap property.
     * 
     * @return
     *     possible object is
     *     {@link LDAPServerDetail }
     *     
     */
    public LDAPServerDetail getLdap() {
        return ldap;
    }

    /**
     * Sets the value of the ldap property.
     * 
     * @param value
     *     allowed object is
     *     {@link LDAPServerDetail }
     *     
     */
    public void setLdap(LDAPServerDetail value) {
        this.ldap = value;
    }

    /**
     * Gets the value of the db property.
     * 
     * @return
     *     possible object is
     *     {@link DBServerDetail }
     *     
     */
    public DBServerDetail getDb() {
        return db;
    }

    /**
     * Sets the value of the db property.
     * 
     * @param value
     *     allowed object is
     *     {@link DBServerDetail }
     *     
     */
    public void setDb(DBServerDetail value) {
        this.db = value;
    }

    /**
     * Gets the value of the appServer property.
     * 
     * @return
     *     possible object is
     *     {@link AppServerDetail }
     *     
     */
    public AppServerDetail getAppServer() {
        return appServer;
    }

    /**
     * Sets the value of the appServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppServerDetail }
     *     
     */
    public void setAppServer(AppServerDetail value) {
        this.appServer = value;
    }

    /**
     * Gets the value of the passwordServer property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordServerDetail }
     *     
     */
    public PasswordServerDetail getPasswordServer() {
        return passwordServer;
    }

    /**
     * Sets the value of the passwordServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordServerDetail }
     *     
     */
    public void setPasswordServer(PasswordServerDetail value) {
        this.passwordServer = value;
    }

    /**
     * Gets the value of the webServer property.
     * 
     * @return
     *     possible object is
     *     {@link WebServerDetail }
     *     
     */
    public WebServerDetail getWebServer() {
        return webServer;
    }

    /**
     * Sets the value of the webServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebServerDetail }
     *     
     */
    public void setWebServer(WebServerDetail value) {
        this.webServer = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationDetail }
     *     
     */
    public ApplicationDetail getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationDetail }
     *     
     */
    public void setApplication(ApplicationDetail value) {
        this.application = value;
    }

}
