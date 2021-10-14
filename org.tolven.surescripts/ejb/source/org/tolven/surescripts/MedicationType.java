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
 * <p>Java class for MedicationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MedicationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugDescription" type="{http://www.surescripts.com/messaging}an..105M"/>
 *         &lt;element name="DrugCoded" type="{http://www.surescripts.com/messaging}DrugCodedType" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.surescripts.com/messaging}QuantityType"/>
 *         &lt;element name="DaysSupply" type="{http://www.surescripts.com/messaging}n..3M" minOccurs="0"/>
 *         &lt;element name="Directions" type="{http://www.surescripts.com/messaging}an..140" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.surescripts.com/messaging}an..210" minOccurs="0"/>
 *         &lt;element name="Refills" type="{http://www.surescripts.com/messaging}RefillsType" minOccurs="0"/>
 *         &lt;element name="Substitutions" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="0|1|2|3|4|5|7|8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="WrittenDate" type="{http://www.surescripts.com/messaging}DateType" minOccurs="0"/>
 *         &lt;element name="LastFillDate" type="{http://www.surescripts.com/messaging}DateType" minOccurs="0"/>
 *         &lt;element name="Diagnosis" maxOccurs="2" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ClinicalInformationQualifier">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;pattern value="PrescriberSupplied|PharmacyInferred"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Primary" type="{http://www.surescripts.com/messaging}DiagnosisType"/>
 *                   &lt;element name="Secondary" type="{http://www.surescripts.com/messaging}DiagnosisType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PriorAuthorization" type="{http://www.surescripts.com/messaging}PriorAuthorizationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicationType", propOrder = {
    "drugDescription",
    "drugCoded",
    "quantity",
    "daysSupply",
    "directions",
    "note",
    "refills",
    "substitutions",
    "writtenDate",
    "lastFillDate",
    "diagnosis",
    "priorAuthorization"
})
public class MedicationType implements Serializable{

    @XmlElement(name = "DrugDescription", required = true)
    protected String drugDescription;
    @XmlElement(name = "DrugCoded")
    protected DrugCodedType drugCoded;
    @XmlElement(name = "Quantity", required = true)
    protected QuantityType quantity;
    @XmlElement(name = "DaysSupply")
    protected String daysSupply;
    @XmlElement(name = "Directions")
    protected String directions;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "Refills")
    protected RefillsType refills;
    @XmlElement(name = "Substitutions")
    protected String substitutions;
    @XmlElement(name = "WrittenDate")
    protected String writtenDate;
    @XmlElement(name = "LastFillDate")
    protected String lastFillDate;
    @XmlElement(name = "Diagnosis")
    protected List<MedicationType.Diagnosis> diagnosis;
    @XmlElement(name = "PriorAuthorization")
    protected PriorAuthorizationType priorAuthorization;

    /**
     * Gets the value of the drugDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugDescription() {
        return drugDescription;
    }

    /**
     * Sets the value of the drugDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugDescription(String value) {
        this.drugDescription = value;
    }

    /**
     * Gets the value of the drugCoded property.
     * 
     * @return
     *     possible object is
     *     {@link DrugCodedType }
     *     
     */
    public DrugCodedType getDrugCoded() {
        return drugCoded;
    }

    /**
     * Sets the value of the drugCoded property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugCodedType }
     *     
     */
    public void setDrugCoded(DrugCodedType value) {
        this.drugCoded = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setQuantity(QuantityType value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the daysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaysSupply() {
        return daysSupply;
    }

    /**
     * Sets the value of the daysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysSupply(String value) {
        this.daysSupply = value;
    }

    /**
     * Gets the value of the directions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Sets the value of the directions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirections(String value) {
        this.directions = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the refills property.
     * 
     * @return
     *     possible object is
     *     {@link RefillsType }
     *     
     */
    public RefillsType getRefills() {
        return refills;
    }

    /**
     * Sets the value of the refills property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefillsType }
     *     
     */
    public void setRefills(RefillsType value) {
        this.refills = value;
    }

    /**
     * Gets the value of the substitutions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutions() {
        return substitutions;
    }

    /**
     * Sets the value of the substitutions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutions(String value) {
        this.substitutions = value;
    }

    /**
     * Gets the value of the writtenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrittenDate() {
        return writtenDate;
    }

    /**
     * Sets the value of the writtenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrittenDate(String value) {
        this.writtenDate = value;
    }

    /**
     * Gets the value of the lastFillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFillDate() {
        return lastFillDate;
    }

    /**
     * Sets the value of the lastFillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFillDate(String value) {
        this.lastFillDate = value;
    }

    /**
     * Gets the value of the diagnosis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diagnosis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiagnosis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedicationType.Diagnosis }
     * 
     * 
     */
    public List<MedicationType.Diagnosis> getDiagnosis() {
        if (diagnosis == null) {
            diagnosis = new ArrayList<MedicationType.Diagnosis>();
        }
        return this.diagnosis;
    }

    /**
     * Gets the value of the priorAuthorization property.
     * 
     * @return
     *     possible object is
     *     {@link PriorAuthorizationType }
     *     
     */
    public PriorAuthorizationType getPriorAuthorization() {
        return priorAuthorization;
    }

    /**
     * Sets the value of the priorAuthorization property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriorAuthorizationType }
     *     
     */
    public void setPriorAuthorization(PriorAuthorizationType value) {
        this.priorAuthorization = value;
    }


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
     *         &lt;element name="ClinicalInformationQualifier">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="PrescriberSupplied|PharmacyInferred"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Primary" type="{http://www.surescripts.com/messaging}DiagnosisType"/>
     *         &lt;element name="Secondary" type="{http://www.surescripts.com/messaging}DiagnosisType" minOccurs="0"/>
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
        "clinicalInformationQualifier",
        "primary",
        "secondary"
    })
    public static class Diagnosis {

        @XmlElement(name = "ClinicalInformationQualifier", required = true)
        protected String clinicalInformationQualifier;
        @XmlElement(name = "Primary", required = true)
        protected DiagnosisType primary;
        @XmlElement(name = "Secondary")
        protected DiagnosisType secondary;

        /**
         * Gets the value of the clinicalInformationQualifier property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClinicalInformationQualifier() {
            return clinicalInformationQualifier;
        }

        /**
         * Sets the value of the clinicalInformationQualifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClinicalInformationQualifier(String value) {
            this.clinicalInformationQualifier = value;
        }

        /**
         * Gets the value of the primary property.
         * 
         * @return
         *     possible object is
         *     {@link DiagnosisType }
         *     
         */
        public DiagnosisType getPrimary() {
            return primary;
        }

        /**
         * Sets the value of the primary property.
         * 
         * @param value
         *     allowed object is
         *     {@link DiagnosisType }
         *     
         */
        public void setPrimary(DiagnosisType value) {
            this.primary = value;
        }

        /**
         * Gets the value of the secondary property.
         * 
         * @return
         *     possible object is
         *     {@link DiagnosisType }
         *     
         */
        public DiagnosisType getSecondary() {
            return secondary;
        }

        /**
         * Sets the value of the secondary property.
         * 
         * @param value
         *     allowed object is
         *     {@link DiagnosisType }
         *     
         */
        public void setSecondary(DiagnosisType value) {
            this.secondary = value;
        }

    }

}
