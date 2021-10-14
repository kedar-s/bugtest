//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.07.22 at 03:20:48 PM PDT 
//


package org.tolven.config.model.credential.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CertificateGroupDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateGroupDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tolven-org:credentials:1.0}GroupDetail">
 *       &lt;sequence>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organizationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organizationUnitName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certificate" type="{urn:tolven-org:credentials:1.0}CertificateDetail" minOccurs="0"/>
 *         &lt;element name="key" type="{urn:tolven-org:credentials:1.0}CertificateKeyDetail" minOccurs="0"/>
 *         &lt;element name="keyStore" type="{urn:tolven-org:credentials:1.0}CertificateKeyStoreDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateGroupDetail", propOrder = {
    "countryName",
    "stateOrProvince",
    "organizationName",
    "organizationUnitName",
    "commonName",
    "email",
    "certificate",
    "key",
    "keyStore"
})
public class CertificateGroupDetail
    extends GroupDetail
{

    @XmlElement(required = true)
    protected String countryName;
    @XmlElement(required = true)
    protected String stateOrProvince;
    @XmlElement(required = true)
    protected String organizationName;
    @XmlElement(required = true)
    protected String organizationUnitName;
    @XmlElement(required = true)
    protected String commonName;
    @XmlElement(required = true)
    protected String email;
    protected CertificateDetail certificate;
    protected CertificateKeyDetail key;
    protected CertificateKeyStoreDetail keyStore;

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the stateOrProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Sets the value of the stateOrProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateOrProvince(String value) {
        this.stateOrProvince = value;
    }

    /**
     * Gets the value of the organizationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the value of the organizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationName(String value) {
        this.organizationName = value;
    }

    /**
     * Gets the value of the organizationUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationUnitName() {
        return organizationUnitName;
    }

    /**
     * Sets the value of the organizationUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationUnitName(String value) {
        this.organizationUnitName = value;
    }

    /**
     * Gets the value of the commonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets the value of the commonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommonName(String value) {
        this.commonName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateDetail }
     *     
     */
    public CertificateDetail getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateDetail }
     *     
     */
    public void setCertificate(CertificateDetail value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateKeyDetail }
     *     
     */
    public CertificateKeyDetail getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateKeyDetail }
     *     
     */
    public void setKey(CertificateKeyDetail value) {
        this.key = value;
    }

    /**
     * Gets the value of the keyStore property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateKeyStoreDetail }
     *     
     */
    public CertificateKeyStoreDetail getKeyStore() {
        return keyStore;
    }

    /**
     * Sets the value of the keyStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateKeyStoreDetail }
     *     
     */
    public void setKeyStore(CertificateKeyStoreDetail value) {
        this.keyStore = value;
    }

}
