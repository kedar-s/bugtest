
package org.tolven.client.examples.ws.trim.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewFacet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewFacet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="encoded" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="datatype" type="{urn:tolven-org:trim:4.0}ConcreteDatatype" />
 *       &lt;attribute name="function" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewFacet", propOrder = {
    "encoded"
})
public class NewFacet {

    protected String encoded;
    @XmlAttribute(name = "datatype")
    protected ConcreteDatatype datatype;
    @XmlAttribute(name = "function")
    protected String function;

    /**
     * Gets the value of the encoded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoded() {
        return encoded;
    }

    /**
     * Sets the value of the encoded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoded(String value) {
        this.encoded = value;
    }

    /**
     * Gets the value of the datatype property.
     * 
     * @return
     *     possible object is
     *     {@link ConcreteDatatype }
     *     
     */
    public ConcreteDatatype getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConcreteDatatype }
     *     
     */
    public void setDatatype(ConcreteDatatype value) {
        this.datatype = value;
    }

    /**
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunction(String value) {
        this.function = value;
    }

}
