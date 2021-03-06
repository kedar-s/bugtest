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
 * <p>Java class for MandatoryZipCodeAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MandatoryZipCodeAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressLine1" type="{http://www.surescripts.com/messaging}an..35M" minOccurs="0"/>
 *         &lt;element name="AddressLine2" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.surescripts.com/messaging}an..35M" minOccurs="0"/>
 *         &lt;element name="State" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="((A|a)(L|l))|((A|a)(K|k))|((A|a)(S|s))|((A|a)(Z|z))|((A|a)(R|r))|((C|c)(A|a))|((C|c)(O|o))|((C|c)(T|t))|((D|d)(E|e))|((D|d)(C|c))|((F|f)(M|m))|((F|f)(L|l))|((G|g)(A|a))|((G|g)(U|u))|((H|h)(I|i))|((I|i)(D|d))|((I|i)(L|l))|((I|i)(N|n))|((I|i)(A|a))|((K|k)(S|s))|((K|k)(Y|y))|((L|l)(A|a))|((M|m)(E|e))|((M|m)(H|h))|((M|m)(D|d))|((M|m)(A|a))|((M|m)(I|i))|((M|m)(N|n))|((M|m)(S|s))|((M|m)(O|o))|((M|m)(T|t))|((N|n)(E|e))|((N|n)(V|v))|((N|n)(H|h))|((N|n)(J|j))|((N|n)(M|m))|((N|n)(Y|y))|((N|n)(C|c))|((N|n)(D|d))|((M|m)(P|p))|((O|o)(H|h))|((O|o)(K|k))|((O|o)(R|r))|((P|p)(W|w))|((P|p)(A|a))|((P|p)(R|r))|((R|r)(I|i))|((S|s)(C|c))|((S|s)(D|d))|((T|t)(N|n))|((T|t)(X|x))|((U|u)(T|t))|((V|v)(T|t))|((V|v)(I|i))|((V|v)(A|a))|((W|w)(A|a))|((W|w)(V|v))|((W|w)(I|i))|((W|w)(Y|y))|((A|a)(E|e))|((A|a)(A|a))|((A|a)(P|p))"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZipCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(\d{5})|(\d{9})"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandatoryZipCodeAddressType", propOrder = {
    "addressLine1",
    "addressLine2",
    "city",
    "state",
    "zipCode"
})
public class MandatoryZipCodeAddressType implements Serializable{

    @XmlElement(name = "AddressLine1")
    protected String addressLine1;
    @XmlElement(name = "AddressLine2")
    protected String addressLine2;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "ZipCode", required = true)
    protected String zipCode;

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

}
