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
 * <p>Java class for HeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="To" type="{http://www.surescripts.com/messaging}MailAddressType"/>
 *         &lt;element name="From" type="{http://www.surescripts.com/messaging}MailAddressType"/>
 *         &lt;element name="MessageID" type="{http://www.surescripts.com/messaging}an..35M"/>
 *         &lt;element name="RelatesToMessageID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="SentTime" type="{http://www.surescripts.com/messaging}UtcDateType"/>
 *         &lt;element name="Security" type="{http://www.surescripts.com/messaging}SecurityType" minOccurs="0"/>
 *         &lt;element name="SMSVersion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="4|4.0|4.20|4.2|4.21"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AppVersion" type="{http://www.surescripts.com/messaging}AppVersionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderType", propOrder = {
    "to",
    "from",
    "messageID",
    "relatesToMessageID",
    "sentTime",
    "security",
    "smsVersion",
    "appVersion"
})
public class HeaderType implements Serializable{

    @XmlElement(name = "To", required = true)
    protected String to;
    @XmlElement(name = "From", required = true)
    protected String from;
    @XmlElement(name = "MessageID", required = true)
    protected String messageID;
    @XmlElement(name = "RelatesToMessageID")
    protected String relatesToMessageID;
    @XmlElement(name = "SentTime", required = true)
    protected String sentTime;
    @XmlElement(name = "Security")
    protected SecurityType security;
    @XmlElement(name = "SMSVersion")
    protected String smsVersion;
    @XmlElement(name = "AppVersion")
    protected AppVersionType appVersion;

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the relatesToMessageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatesToMessageID() {
        return relatesToMessageID;
    }

    /**
     * Sets the value of the relatesToMessageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatesToMessageID(String value) {
        this.relatesToMessageID = value;
    }

    /**
     * Gets the value of the sentTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentTime() {
        return sentTime;
    }

    /**
     * Sets the value of the sentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentTime(String value) {
        this.sentTime = value;
    }

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityType }
     *     
     */
    public SecurityType getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityType }
     *     
     */
    public void setSecurity(SecurityType value) {
        this.security = value;
    }

    /**
     * Gets the value of the smsVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSVersion() {
        return smsVersion;
    }

    /**
     * Sets the value of the smsVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSVersion(String value) {
        this.smsVersion = value;
    }

    /**
     * Gets the value of the appVersion property.
     * 
     * @return
     *     possible object is
     *     {@link AppVersionType }
     *     
     */
    public AppVersionType getAppVersion() {
        return appVersion;
    }

    /**
     * Sets the value of the appVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppVersionType }
     *     
     */
    public void setAppVersion(AppVersionType value) {
        this.appVersion = value;
    }

}