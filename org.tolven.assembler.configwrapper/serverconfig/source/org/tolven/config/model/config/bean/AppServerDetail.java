//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.07.22 at 03:20:53 PM PDT 
//


package org.tolven.config.model.config.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppServerDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppServerDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tolven-org:tolven-config:1.0}EntityDetail">
 *       &lt;sequence>
 *         &lt;element name="authRestful" type="{urn:tolven-org:tolven-config:1.0}RestfulDetail"/>
 *         &lt;element name="appRestful" type="{urn:tolven-org:tolven-config:1.0}RestfulDetail"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dbId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ldapId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rootPassId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppServerDetail", propOrder = {
    "authRestful",
    "appRestful"
})
public class AppServerDetail
    extends EntityDetail
{

    @XmlElement(required = true)
    protected RestfulDetail authRestful;
    @XmlElement(required = true)
    protected RestfulDetail appRestful;
    @XmlAttribute
    protected String dbId;
    @XmlAttribute
    protected String ldapId;
    @XmlAttribute(required = true)
    protected String rootPassId;

    /**
     * Gets the value of the authRestful property.
     * 
     * @return
     *     possible object is
     *     {@link RestfulDetail }
     *     
     */
    public RestfulDetail getAuthRestful() {
        return authRestful;
    }

    /**
     * Sets the value of the authRestful property.
     * 
     * @param value
     *     allowed object is
     *     {@link RestfulDetail }
     *     
     */
    public void setAuthRestful(RestfulDetail value) {
        this.authRestful = value;
    }

    /**
     * Gets the value of the appRestful property.
     * 
     * @return
     *     possible object is
     *     {@link RestfulDetail }
     *     
     */
    public RestfulDetail getAppRestful() {
        return appRestful;
    }

    /**
     * Sets the value of the appRestful property.
     * 
     * @param value
     *     allowed object is
     *     {@link RestfulDetail }
     *     
     */
    public void setAppRestful(RestfulDetail value) {
        this.appRestful = value;
    }

    /**
     * Gets the value of the dbId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbId() {
        return dbId;
    }

    /**
     * Sets the value of the dbId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbId(String value) {
        this.dbId = value;
    }

    /**
     * Gets the value of the ldapId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapId() {
        return ldapId;
    }

    /**
     * Sets the value of the ldapId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapId(String value) {
        this.ldapId = value;
    }

    /**
     * Gets the value of the rootPassId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootPassId() {
        return rootPassId;
    }

    /**
     * Sets the value of the rootPassId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootPassId(String value) {
        this.rootPassId = value;
    }

}
