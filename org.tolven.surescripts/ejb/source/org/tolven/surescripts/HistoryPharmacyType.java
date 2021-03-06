//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.09 at 11:44:06 AM GMT+05:30 
//


package org.tolven.surescripts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HistoryPharmacyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HistoryPharmacyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identification" type="{http://www.surescripts.com/messaging}PharmacyIDType" minOccurs="0"/>
 *         &lt;element name="StoreName" type="{http://www.surescripts.com/messaging}an..35M"/>
 *         &lt;element name="Pharmacist" type="{http://www.surescripts.com/messaging}NameType" minOccurs="0"/>
 *         &lt;element name="PharmacistAgent" type="{http://www.surescripts.com/messaging}MandatoryNameType" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://www.surescripts.com/messaging}AddressType" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.surescripts.com/messaging}an..80" minOccurs="0"/>
 *         &lt;element name="PhoneNumbers" type="{http://www.surescripts.com/messaging}PhoneNumbersType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoryPharmacyType", propOrder = {
    "identification",
    "storeName",
    "pharmacist",
    "pharmacistAgent",
    "address",
    "email",
    "phoneNumbers"
})
public class HistoryPharmacyType implements Serializable{

    @XmlElement(name = "Identification")
    protected PharmacyIDType identification;
    @XmlElement(name = "StoreName", required = true)
    protected String storeName;
    @XmlElement(name = "Pharmacist")
    protected NameType pharmacist;
    @XmlElement(name = "PharmacistAgent")
    protected MandatoryNameType pharmacistAgent;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "PhoneNumbers")
    protected List<PhoneNumbersType> phoneNumbers;

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyIDType }
     *     
     */
    public PharmacyIDType getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyIDType }
     *     
     */
    public void setIdentification(PharmacyIDType value) {
        this.identification = value;
    }

    /**
     * Gets the value of the storeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Sets the value of the storeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreName(String value) {
        this.storeName = value;
    }

    /**
     * Gets the value of the pharmacist property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getPharmacist() {
        return pharmacist;
    }

    /**
     * Sets the value of the pharmacist property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setPharmacist(NameType value) {
        this.pharmacist = value;
    }

    /**
     * Gets the value of the pharmacistAgent property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryNameType }
     *     
     */
    public MandatoryNameType getPharmacistAgent() {
        return pharmacistAgent;
    }

    /**
     * Sets the value of the pharmacistAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryNameType }
     *     
     */
    public void setPharmacistAgent(MandatoryNameType value) {
        this.pharmacistAgent = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
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
     * Gets the value of the phoneNumbers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNumbers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNumbers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumbersType }
     * 
     * 
     */
    public List<PhoneNumbersType> getPhoneNumbers() {
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<PhoneNumbersType>();
        }
        return this.phoneNumbers;
    }

}
