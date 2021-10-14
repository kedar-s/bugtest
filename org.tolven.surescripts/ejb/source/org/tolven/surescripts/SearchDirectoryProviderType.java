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
 * <p>Java class for SearchDirectoryProviderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchDirectoryProviderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DirectoryInformation" type="{http://www.surescripts.com/messaging}SearchDirectoryInformationType" minOccurs="0"/>
 *         &lt;element name="Taxonomy" type="{http://www.surescripts.com/messaging}TaxonomyType"/>
 *         &lt;element name="Identification" type="{http://www.surescripts.com/messaging}IdentificationType" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://www.surescripts.com/messaging}AddressType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchDirectoryProviderType", propOrder = {
    "directoryInformation",
    "taxonomy",
    "identification",
    "address"
})
public class SearchDirectoryProviderType implements Serializable{

    @XmlElement(name = "DirectoryInformation")
    protected SearchDirectoryInformationType directoryInformation;
    @XmlElement(name = "Taxonomy", required = true)
    protected TaxonomyType taxonomy;
    @XmlElement(name = "Identification")
    protected IdentificationType identification;
    @XmlElement(name = "Address")
    protected AddressType address;

    /**
     * Gets the value of the directoryInformation property.
     * 
     * @return
     *     possible object is
     *     {@link SearchDirectoryInformationType }
     *     
     */
    public SearchDirectoryInformationType getDirectoryInformation() {
        return directoryInformation;
    }

    /**
     * Sets the value of the directoryInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchDirectoryInformationType }
     *     
     */
    public void setDirectoryInformation(SearchDirectoryInformationType value) {
        this.directoryInformation = value;
    }

    /**
     * Gets the value of the taxonomy property.
     * 
     * @return
     *     possible object is
     *     {@link TaxonomyType }
     *     
     */
    public TaxonomyType getTaxonomy() {
        return taxonomy;
    }

    /**
     * Sets the value of the taxonomy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxonomyType }
     *     
     */
    public void setTaxonomy(TaxonomyType value) {
        this.taxonomy = value;
    }

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link IdentificationType }
     *     
     */
    public IdentificationType getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificationType }
     *     
     */
    public void setIdentification(IdentificationType value) {
        this.identification = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

}
