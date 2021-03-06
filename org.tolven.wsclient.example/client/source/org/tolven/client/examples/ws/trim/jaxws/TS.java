
package org.tolven.client.examples.ws.trim.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TS">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tolven-org:trim:4.0}QTY">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="w3c" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TS", propOrder = {
    "value",
    "w3C"
})
@XmlSeeAlso({
    TSDATETIME.class,
    TSDATETIMEFULL.class,
    TSDATE.class,
    TSDATEFULL.class,
    TSBIRTH.class
})
public class TS
    extends QTY
{

    protected String value;
    @XmlElement(name = "w3c")
    @XmlSchemaType(name = "anySimpleType")
    protected Object w3C;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the w3C property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getW3C() {
        return w3C;
    }

    /**
     * Sets the value of the w3C property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setW3C(Object value) {
        this.w3C = value;
    }

}
