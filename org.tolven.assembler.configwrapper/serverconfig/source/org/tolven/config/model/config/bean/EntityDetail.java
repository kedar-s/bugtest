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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="trustStoreId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="credentialDir" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityDetail")
@XmlSeeAlso({
    AdminDetail.class,
    LDAPServerDetail.class,
    DBServerDetail.class,
    AppServerDetail.class,
    PasswordServerDetail.class,
    WebServerDetail.class,
    MDBUserDetail.class
})
public class EntityDetail {

    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute
    protected String trustStoreId;
    @XmlAttribute
    protected String credentialDir;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the trustStoreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustStoreId() {
        return trustStoreId;
    }

    /**
     * Sets the value of the trustStoreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustStoreId(String value) {
        this.trustStoreId = value;
    }

    /**
     * Gets the value of the credentialDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialDir() {
        return credentialDir;
    }

    /**
     * Sets the value of the credentialDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialDir(String value) {
        this.credentialDir = value;
    }

}
