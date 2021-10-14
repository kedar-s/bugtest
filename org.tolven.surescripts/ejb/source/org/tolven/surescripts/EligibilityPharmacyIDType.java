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
 * <p>Java class for EligibilityPharmacyIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityPharmacyIDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="3">
 *         &lt;element name="MemberNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="NPI" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="SubmitterNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="StateLicenseNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MedicareNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MedicaidNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="FacilityNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PersonalIDNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="ContractNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="ElectronicDevicePin" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="HCFA" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="UserID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PPONumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="FacilityNetworkNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PriorAuthorization" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="TIN" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
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
@XmlType(name = "EligibilityPharmacyIDType", propOrder = {
    "memberNumberOrNPIOrSubmitterNumber"
})
public class EligibilityPharmacyIDType implements Serializable{

    @XmlElementRefs({
        @XmlElementRef(name = "PriorAuthorization", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PPONumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MemberNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "StateLicenseNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "UserID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "SubmitterNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MedicareNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "FacilityNetworkNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "NPI", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PersonalIDNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "FacilityNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MedicaidNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "ElectronicDevicePin", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MutuallyDefined", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "ContractNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "HCFA", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "TIN", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> memberNumberOrNPIOrSubmitterNumber;

    /**
     * Gets the value of the memberNumberOrNPIOrSubmitterNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the memberNumberOrNPIOrSubmitterNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMemberNumberOrNPIOrSubmitterNumber().add(newItem);
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
    public List<JAXBElement<String>> getMemberNumberOrNPIOrSubmitterNumber() {
        if (memberNumberOrNPIOrSubmitterNumber == null) {
            memberNumberOrNPIOrSubmitterNumber = new ArrayList<JAXBElement<String>>();
        }
        return this.memberNumberOrNPIOrSubmitterNumber;
    }

}
