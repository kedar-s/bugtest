
package org.tolven.client.examples.ws.trim.jaxws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityNameUse.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EntityNameUse">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="L"/>
 *     &lt;enumeration value="OR"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="PHON"/>
 *     &lt;enumeration value="SNDX"/>
 *     &lt;enumeration value="SRCH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EntityNameUse")
@XmlEnum
public enum EntityNameUse {

    A,
    C,
    L,
    OR,
    P,
    PHON,
    SNDX,
    SRCH;

    public String value() {
        return name();
    }

    public static EntityNameUse fromValue(String v) {
        return valueOf(v);
    }

}
