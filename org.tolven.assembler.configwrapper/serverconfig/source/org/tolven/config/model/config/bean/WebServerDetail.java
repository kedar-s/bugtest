//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.07.22 at 03:20:53 PM PDT 
//


package org.tolven.config.model.config.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebServerDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebServerDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tolven-org:tolven-config:1.0}EntityDetail">
 *       &lt;attribute name="restfulURL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebServerDetail")
public class WebServerDetail
    extends EntityDetail
{

    @XmlAttribute(required = true)
    protected String restfulURL;

    /**
     * Gets the value of the restfulURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestfulURL() {
        return restfulURL;
    }

    /**
     * Sets the value of the restfulURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestfulURL(String value) {
        this.restfulURL = value;
    }

}
