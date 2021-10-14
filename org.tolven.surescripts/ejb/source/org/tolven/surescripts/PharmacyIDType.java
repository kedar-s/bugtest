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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PharmacyIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PharmacyIDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="3">
 *         &lt;element name="NCPDPID" type="{http://www.surescripts.com/messaging}an..35M"/>
 *         &lt;element name="FileID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="StateLicenseNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MedicareNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MedicaidNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PPONumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PayerID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="BINLocationNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="DEANumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="HIN" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="SecondaryCoverage" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="NAICCode" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PromotionNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="SocialSecurity" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="NPI" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PriorAuthorization" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MutuallyDefined" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmacyIDType", propOrder = {
    "ncpdpidOrFileIDOrStateLicenseNumber"
})
public class PharmacyIDType implements Serializable{

    @XmlElementRefs({
        @XmlElementRef(name = "StateLicenseNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "SocialSecurity", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "DEANumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MedicaidNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "FileID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "NPI", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "NAICCode", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PayerID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MedicareNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MutuallyDefined", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "NCPDPID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PriorAuthorization", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "BINLocationNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PromotionNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "SecondaryCoverage", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PPONumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "HIN", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> ncpdpidOrFileIDOrStateLicenseNumber;

    /**
     * Gets the value of the ncpdpidOrFileIDOrStateLicenseNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ncpdpidOrFileIDOrStateLicenseNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNCPDPIDOrFileIDOrStateLicenseNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getNCPDPIDOrFileIDOrStateLicenseNumber() {
        if (ncpdpidOrFileIDOrStateLicenseNumber == null) {
            ncpdpidOrFileIDOrStateLicenseNumber = new ArrayList<JAXBElement<String>>();
        }
        return this.ncpdpidOrFileIDOrStateLicenseNumber;
    }

}
