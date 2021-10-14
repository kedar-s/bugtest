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
 * <p>Java class for EligibilityPatientIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityPatientIDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="2">
 *         &lt;element name="SocialSecurity" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PlanNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="CardHolderID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PersonCode" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="GroupNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="FormularyList" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="CoverageListID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="BIN" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="CoPayID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="FileID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PayerID" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="PriorAuthorization" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="MutuallyDefined" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="ZZ" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EligibilityPatientIDType", propOrder = {
    "socialSecurityOrPlanNumberOrCardHolderID"
})
public class EligibilityPatientIDType implements Serializable{

    @XmlElementRefs({
        @XmlElementRef(name = "CardHolderID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PlanNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "FileID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "CoPayID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "FormularyList", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PayerID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "SocialSecurity", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PersonCode", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "CoverageListID", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "ZZ", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "BIN", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "GroupNumber", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "PriorAuthorization", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class),
        @XmlElementRef(name = "MutuallyDefined", namespace = "http://www.surescripts.com/messaging", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> socialSecurityOrPlanNumberOrCardHolderID;

    /**
     * Gets the value of the socialSecurityOrPlanNumberOrCardHolderID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the socialSecurityOrPlanNumberOrCardHolderID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSocialSecurityOrPlanNumberOrCardHolderID().add(newItem);
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
     * 
     * 
     */
    public List<JAXBElement<String>> getSocialSecurityOrPlanNumberOrCardHolderID() {
        if (socialSecurityOrPlanNumberOrCardHolderID == null) {
            socialSecurityOrPlanNumberOrCardHolderID = new ArrayList<JAXBElement<String>>();
        }
        return this.socialSecurityOrPlanNumberOrCardHolderID;
    }

}