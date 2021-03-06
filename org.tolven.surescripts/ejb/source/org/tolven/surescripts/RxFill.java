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
 *         &lt;element name="RxReferenceNumber" type="{http://www.surescripts.com/messaging}an..35M"/>
 *         &lt;element name="PrescriberOrderNumber" type="{http://www.surescripts.com/messaging}an..35" minOccurs="0"/>
 *         &lt;element name="FillStatus" type="{http://www.surescripts.com/messaging}FillStatusType"/>
 *         &lt;element name="Pharmacy" type="{http://www.surescripts.com/messaging}MandatoryPharmacyType"/>
 *         &lt;element name="Prescriber" type="{http://www.surescripts.com/messaging}PrescriberType"/>
 *         &lt;element name="Supervisor" type="{http://www.surescripts.com/messaging}SupervisorType" minOccurs="0"/>
 *         &lt;element name="Patient" type="{http://www.surescripts.com/messaging}PatientType"/>
 *         &lt;element name="MedicationPrescribed" type="{http://www.surescripts.com/messaging}MedicationType"/>
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
    "rxReferenceNumber",
    "prescriberOrderNumber",
    "fillStatus",
    "pharmacy",
    "prescriber",
    "supervisor",
    "patient",
    "medicationPrescribed"
})
@XmlRootElement(name = "RxFill")
public class RxFill implements Serializable{

    @XmlElement(name = "RxReferenceNumber", required = true)
    protected String rxReferenceNumber;
    @XmlElement(name = "PrescriberOrderNumber")
    protected String prescriberOrderNumber;
    @XmlElement(name = "FillStatus", required = true)
    protected FillStatusType fillStatus;
    @XmlElement(name = "Pharmacy", required = true)
    protected MandatoryPharmacyType pharmacy;
    @XmlElement(name = "Prescriber", required = true)
    protected PrescriberType prescriber;
    @XmlElement(name = "Supervisor")
    protected SupervisorType supervisor;
    @XmlElement(name = "Patient", required = true)
    protected PatientType patient;
    @XmlElement(name = "MedicationPrescribed", required = true)
    protected MedicationType medicationPrescribed;

    /**
     * Gets the value of the rxReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRxReferenceNumber() {
        return rxReferenceNumber;
    }

    /**
     * Sets the value of the rxReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRxReferenceNumber(String value) {
        this.rxReferenceNumber = value;
    }

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
     * Gets the value of the fillStatus property.
     * 
     * @return
     *     possible object is
     *     {@link FillStatusType }
     *     
     */
    public FillStatusType getFillStatus() {
        return fillStatus;
    }

    /**
     * Sets the value of the fillStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillStatusType }
     *     
     */
    public void setFillStatus(FillStatusType value) {
        this.fillStatus = value;
    }

    /**
     * Gets the value of the pharmacy property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryPharmacyType }
     *     
     */
    public MandatoryPharmacyType getPharmacy() {
        return pharmacy;
    }

    /**
     * Sets the value of the pharmacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryPharmacyType }
     *     
     */
    public void setPharmacy(MandatoryPharmacyType value) {
        this.pharmacy = value;
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
     * Gets the value of the supervisor property.
     * 
     * @return
     *     possible object is
     *     {@link SupervisorType }
     *     
     */
    public SupervisorType getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the value of the supervisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupervisorType }
     *     
     */
    public void setSupervisor(SupervisorType value) {
        this.supervisor = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link PatientType }
     *     
     */
    public PatientType getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientType }
     *     
     */
    public void setPatient(PatientType value) {
        this.patient = value;
    }

    /**
     * Gets the value of the medicationPrescribed property.
     * 
     * @return
     *     possible object is
     *     {@link MedicationType }
     *     
     */
    public MedicationType getMedicationPrescribed() {
        return medicationPrescribed;
    }

    /**
     * Sets the value of the medicationPrescribed property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedicationType }
     *     
     */
    public void setMedicationPrescribed(MedicationType value) {
        this.medicationPrescribed = value;
    }

}
