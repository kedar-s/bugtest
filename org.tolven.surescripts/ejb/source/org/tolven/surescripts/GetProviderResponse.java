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
 *         &lt;element name="Response" type="{http://www.surescripts.com/messaging}ResponseTypeDir"/>
 *         &lt;choice>
 *           &lt;element name="Prescriber" type="{http://www.surescripts.com/messaging}DirectoryPrescriberType" maxOccurs="500"/>
 *           &lt;element name="Pharmacy" type="{http://www.surescripts.com/messaging}DirectoryPharmacyType" maxOccurs="500"/>
 *         &lt;/choice>
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
    "response",
    "prescriber",
    "pharmacy"
})
@XmlRootElement(name = "GetProviderResponse")
public class GetProviderResponse implements Serializable{

    @XmlElement(name = "Response", required = true)
    protected ResponseTypeDir response;
    @XmlElement(name = "Prescriber")
    protected List<DirectoryPrescriberType> prescriber;
    @XmlElement(name = "Pharmacy")
    protected List<DirectoryPharmacyType> pharmacy;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseTypeDir }
     *     
     */
    public ResponseTypeDir getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseTypeDir }
     *     
     */
    public void setResponse(ResponseTypeDir value) {
        this.response = value;
    }

    /**
     * Gets the value of the prescriber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prescriber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrescriber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectoryPrescriberType }
     * 
     * 
     */
    public List<DirectoryPrescriberType> getPrescriber() {
        if (prescriber == null) {
            prescriber = new ArrayList<DirectoryPrescriberType>();
        }
        return this.prescriber;
    }

    /**
     * Gets the value of the pharmacy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pharmacy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPharmacy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectoryPharmacyType }
     * 
     * 
     */
    public List<DirectoryPharmacyType> getPharmacy() {
        if (pharmacy == null) {
            pharmacy = new ArrayList<DirectoryPharmacyType>();
        }
        return this.pharmacy;
    }

}
