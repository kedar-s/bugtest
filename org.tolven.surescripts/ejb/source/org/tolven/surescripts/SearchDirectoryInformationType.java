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
 * <p>Java class for SearchDirectoryInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchDirectoryInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceLevel" type="{http://www.surescripts.com/messaging}n..4M" minOccurs="0"/>
 *         &lt;element name="ActiveStartTime" type="{http://www.surescripts.com/messaging}UtcDateType" minOccurs="0"/>
 *         &lt;element name="ActiveEndTime" type="{http://www.surescripts.com/messaging}UtcDateType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchDirectoryInformationType", propOrder = {
    "serviceLevel",
    "activeStartTime",
    "activeEndTime"
})
public class SearchDirectoryInformationType implements Serializable{

    @XmlElement(name = "ServiceLevel")
    protected String serviceLevel;
    @XmlElement(name = "ActiveStartTime")
    protected String activeStartTime;
    @XmlElement(name = "ActiveEndTime")
    protected String activeEndTime;

    /**
     * Gets the value of the serviceLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLevel() {
        return serviceLevel;
    }

    /**
     * Sets the value of the serviceLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLevel(String value) {
        this.serviceLevel = value;
    }

    /**
     * Gets the value of the activeStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveStartTime() {
        return activeStartTime;
    }

    /**
     * Sets the value of the activeStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveStartTime(String value) {
        this.activeStartTime = value;
    }

    /**
     * Gets the value of the activeEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveEndTime() {
        return activeEndTime;
    }

    /**
     * Sets the value of the activeEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveEndTime(String value) {
        this.activeEndTime = value;
    }

}
