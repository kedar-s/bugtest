
package org.tolven.menuStructure;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Placeholder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Placeholder">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tolven-org:menuStructure:1.0}MenuBase">
 *       &lt;sequence>
 *         &lt;element name="field" type="{urn:tolven-org:menuStructure:1.0}PlaceholderField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="menu" type="{urn:tolven-org:menuStructure:1.0}Menu"/>
 *           &lt;element name="portal" type="{urn:tolven-org:menuStructure:1.0}Portal"/>
 *           &lt;element name="timeline" type="{urn:tolven-org:menuStructure:1.0}Timeline"/>
 *           &lt;element name="calendar" type="{urn:tolven-org:menuStructure:1.0}Calendar"/>
 *           &lt;element name="list" type="{urn:tolven-org:menuStructure:1.0}List"/>
 *           &lt;element name="trimList" type="{urn:tolven-org:menuStructure:1.0}TrimList"/>
 *           &lt;element name="instance" type="{urn:tolven-org:menuStructure:1.0}Instance"/>
 *         &lt;/choice>
 *         &lt;element name="placeholder" type="{urn:tolven-org:menuStructure:1.0}Placeholder" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="heading" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="initialSort" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="eventInstance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Placeholder", propOrder = {
    "fields",
    "menusAndPortalsAndTimelines",
    "placeholders"
})
public class Placeholder
    extends MenuBase
    implements Serializable
{

    @XmlElement(name = "field")
    protected java.util.List<PlaceholderField> fields;
    @XmlElements({
        @XmlElement(name = "portal", type = Portal.class),
        @XmlElement(name = "calendar", type = Calendar.class),
        @XmlElement(name = "instance", type = Instance.class),
        @XmlElement(name = "trimList", type = TrimList.class),
        @XmlElement(name = "list", type = org.tolven.menuStructure.List.class),
        @XmlElement(name = "timeline", type = Timeline.class),
        @XmlElement(name = "menu", type = Menu.class)
    })
    protected java.util.List<MenuBase> menusAndPortalsAndTimelines;
    @XmlElement(name = "placeholder")
    protected java.util.List<Placeholder> placeholders;
    @XmlAttribute
    protected String heading;
    @XmlAttribute
    protected String initialSort;
    @XmlAttribute
    protected String eventInstance;

    /**
     * Gets the value of the fields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaceholderField }
     * 
     * 
     */
    public java.util.List<PlaceholderField> getFields() {
        if (fields == null) {
            fields = new ArrayList<PlaceholderField>();
        }
        return this.fields;
    }

    /**
     * Gets the value of the menusAndPortalsAndTimelines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the menusAndPortalsAndTimelines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMenusAndPortalsAndTimelines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Portal }
     * {@link Calendar }
     * {@link Instance }
     * {@link TrimList }
     * {@link org.tolven.menuStructure.List }
     * {@link Timeline }
     * {@link Menu }
     * 
     * 
     */
    public java.util.List<MenuBase> getMenusAndPortalsAndTimelines() {
        if (menusAndPortalsAndTimelines == null) {
            menusAndPortalsAndTimelines = new ArrayList<MenuBase>();
        }
        return this.menusAndPortalsAndTimelines;
    }

    /**
     * Gets the value of the placeholders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placeholders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaceholders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Placeholder }
     * 
     * 
     */
    public java.util.List<Placeholder> getPlaceholders() {
        if (placeholders == null) {
            placeholders = new ArrayList<Placeholder>();
        }
        return this.placeholders;
    }

    /**
     * Gets the value of the heading property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Sets the value of the heading property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeading(String value) {
        this.heading = value;
    }

    /**
     * Gets the value of the initialSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialSort() {
        return initialSort;
    }

    /**
     * Sets the value of the initialSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialSort(String value) {
        this.initialSort = value;
    }

    /**
     * Gets the value of the eventInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventInstance() {
        return eventInstance;
    }

    /**
     * Sets the value of the eventInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventInstance(String value) {
        this.eventInstance = value;
    }

}
