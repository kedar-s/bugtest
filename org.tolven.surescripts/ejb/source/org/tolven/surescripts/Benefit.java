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
 * <p>Java class for Benefit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Benefit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PayerID" type="{http://www.surescripts.com/messaging}an..35"/>
 *         &lt;element name="PayerQualifier">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.surescripts.com/messaging}an..3">
 *               &lt;pattern value="P|U|S|T|PP"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PayerName" type="{http://www.surescripts.com/messaging}an..35"/>
 *         &lt;element name="CardholderID" type="{http://www.surescripts.com/messaging}an..35"/>
 *         &lt;element name="ResponsibleParty" type="{http://www.surescripts.com/messaging}an..35"/>
 *         &lt;element name="GroupID" type="{http://www.surescripts.com/messaging}an..35"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Benefit", propOrder = {
    "payerID",
    "payerQualifier",
    "payerName",
    "cardholderID",
    "responsibleParty",
    "groupID"
})
public class Benefit implements Serializable{

    @XmlElement(name = "PayerID", required = true)
    protected String payerID;
    @XmlElement(name = "PayerQualifier", required = true)
    protected String payerQualifier;
    @XmlElement(name = "PayerName", required = true)
    protected String payerName;
    @XmlElement(name = "CardholderID", required = true)
    protected String cardholderID;
    @XmlElement(name = "ResponsibleParty", required = true)
    protected String responsibleParty;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;

    /**
     * Gets the value of the payerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerID() {
        return payerID;
    }

    /**
     * Sets the value of the payerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerID(String value) {
        this.payerID = value;
    }

    /**
     * Gets the value of the payerQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerQualifier() {
        return payerQualifier;
    }

    /**
     * Sets the value of the payerQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerQualifier(String value) {
        this.payerQualifier = value;
    }

    /**
     * Gets the value of the payerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * Sets the value of the payerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerName(String value) {
        this.payerName = value;
    }

    /**
     * Gets the value of the cardholderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardholderID() {
        return cardholderID;
    }

    /**
     * Sets the value of the cardholderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardholderID(String value) {
        this.cardholderID = value;
    }

    /**
     * Gets the value of the responsibleParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibleParty() {
        return responsibleParty;
    }

    /**
     * Sets the value of the responsibleParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibleParty(String value) {
        this.responsibleParty = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

}
