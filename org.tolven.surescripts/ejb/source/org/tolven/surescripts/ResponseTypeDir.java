//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.09 at 11:44:06 AM GMT+05:30 
//


package org.tolven.surescripts;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseTypeDir complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseTypeDir">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Approved" type="{http://www.surescripts.com/messaging}ApprovedDir"/>
 *         &lt;element name="Denied" type="{http://www.surescripts.com/messaging}DeniedType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseTypeDir", propOrder = {
    "approved",
    "denied"
})
public class ResponseTypeDir implements Serializable{

    @XmlElement(name = "Approved")
    protected ApprovedDir approved;
    @XmlElement(name = "Denied")
    protected DeniedType denied;

    /**
     * Gets the value of the approved property.
     * 
     * @return
     *     possible object is
     *     {@link ApprovedDir }
     *     
     */
    public ApprovedDir getApproved() {
        return approved;
    }

    /**
     * Sets the value of the approved property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovedDir }
     *     
     */
    public void setApproved(ApprovedDir value) {
        this.approved = value;
    }

    /**
     * Gets the value of the denied property.
     * 
     * @return
     *     possible object is
     *     {@link DeniedType }
     *     
     */
    public DeniedType getDenied() {
        return denied;
    }

    /**
     * Sets the value of the denied property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeniedType }
     *     
     */
    public void setDenied(DeniedType value) {
        this.denied = value;
    }

}