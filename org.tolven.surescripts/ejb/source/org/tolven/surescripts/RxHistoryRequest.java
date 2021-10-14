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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrescriberOrderNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="Prescriber" type="{http://www.surescripts.com/messaging}PrescriberType"/>
 *         &lt;element name="Patient" type="{http://www.surescripts.com/messaging}HistoryPatientType"/>
 *         &lt;element name="BenefitsCoordination" type="{http://www.surescripts.com/messaging}BenefitsCoordinationType" maxOccurs="3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prescriberOrderNumber",
    "prescriber",
    "patient",
    "benefitsCoordination"
})
@XmlRootElement(name = "RxHistoryRequest")
public class RxHistoryRequest implements Serializable {

    @XmlElement(name = "PrescriberOrderNumber")
    protected String prescriberOrderNumber;
    @XmlElement(name = "Prescriber", required = true)
    protected PrescriberType prescriber;
    @XmlElement(name = "Patient", required = true)
    protected HistoryPatientType patient;
    @XmlElement(name = "BenefitsCoordination", required = true)
    protected List<BenefitsCoordinationType> benefitsCoordination;

    /**
     * Gets the value of the prescriberOrderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberOrderNumber() {
        return prescriberOrderNumber;
    }

    /**
     * Sets the value of the prescriberOrderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberOrderNumber(String value) {
        this.prescriberOrderNumber = value;
    }

    /**
     * Gets the value of the prescriber property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriberType }
     *     
     */
    public PrescriberType getPrescriber() {
        return prescriber;
    }

    /**
     * Sets the value of the prescriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriberType }
     *     
     */
    public void setPrescriber(PrescriberType value) {
        this.prescriber = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link HistoryPatientType }
     *     
     */
    public HistoryPatientType getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link HistoryPatientType }
     *     
     */
    public void setPatient(HistoryPatientType value) {
        this.patient = value;
    }

    /**
     * Gets the value of the benefitsCoordination property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the benefitsCoordination property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBenefitsCoordination().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BenefitsCoordinationType }
     * 
     * 
     */
    public List<BenefitsCoordinationType> getBenefitsCoordination() {
        if (benefitsCoordination == null) {
            benefitsCoordination = new ArrayList<BenefitsCoordinationType>();
        }
        return this.benefitsCoordination;
    }

}