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
 *         &lt;element name="certificateInfo" type="{urn:tolven-org:credentials:1.0}CertificateInfoDetail"/>
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
    "certificateInfo"
})
@XmlRootElement(name = "credentials")
public class Credentials {

    @XmlElement(required = true)
    protected CertificateInfoDetail certificateInfo;

    /**
     * Gets the value of the certificateInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateInfoDetail }
     *     
     */
    public CertificateInfoDetail getCertificateInfo() {
        return certificateInfo;
    }

    /**
     * Sets the value of the certificateInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateInfoDetail }
     *     
     */
    public void setCertificateInfo(CertificateInfoDetail value) {
        this.certificateInfo = value;
    }

}
