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
 * <p>Java class for EligibilityPatientType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityPatientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identification" type="{http://www.surescripts.com/messaging}EligibilityPatientIDType" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.surescripts.com/messaging}MandatoryNameType"/>
 *         &lt;element name="Gender" type="{http://www.surescripts.com/messaging}GenderType"/>
 *         &lt;element name="DateOfBirth" type="{http://www.surescripts.com/messaging}DateType"/>
 *         &lt;element name="Address" type="{http://www.surescripts.com/messaging}MandatoryZipCodeAddressType"/>
 *         &lt;element name="SubscriberEligibility" type="{http://www.surescripts.com/messaging}SubscriberEligibilityRequestType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EligibilityPatientType", propOrder = {
    "identification",
    "name",
    "gender",
    "dateOfBirth",
    "address",
    "subscriberEligibility"
})
public class EligibilityPatientType implements Serializable{

    @XmlElement(name = "Identification")
    protected EligibilityPatientIDType identification;
    @XmlElement(name = "Name", required = true)
    protected MandatoryNameType name;
    @XmlElement(name = "Gender", required = true)
    protected GenderType gender;
    @XmlElement(name = "DateOfBirth", required = true)
    protected String dateOfBirth;
    @XmlElement(name = "Address", required = true)
    protected MandatoryZipCodeAddressType address;
    @XmlElement(name = "SubscriberEligibility", required = true)
    protected SubscriberEligibilityRequestType subscriberEligibility;

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link EligibilityPatientIDType }
     *     
     */
    public EligibilityPatientIDType getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link EligibilityPatientIDType }
     *     
     */
    public void setIdentification(EligibilityPatientIDType value) {
        this.identification = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryNameType }
     *     
     */
    public MandatoryNameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryNameType }
     *     
     */
    public void setName(MandatoryNameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link GenderType }
     *     
     */
    public GenderType getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderType }
     *     
     */
    public void setGender(GenderType value) {
        this.gender = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryZipCodeAddressType }
     *     
     */
    public MandatoryZipCodeAddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryZipCodeAddressType }
     *     
     */
    public void setAddress(MandatoryZipCodeAddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the subscriberEligibility property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberEligibilityRequestType }
     *     
     */
    public SubscriberEligibilityRequestType getSubscriberEligibility() {
        return subscriberEligibility;
    }

    /**
     * Sets the value of the subscriberEligibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberEligibilityRequestType }
     *     
     */
    public void setSubscriberEligibility(SubscriberEligibilityRequestType value) {
        this.subscriberEligibility = value;
    }

}