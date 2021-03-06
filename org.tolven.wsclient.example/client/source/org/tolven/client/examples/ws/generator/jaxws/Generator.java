
package org.tolven.client.examples.ws.generator.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.5-b05 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "generator", targetNamespace = "http://tolven.org/generator")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Generator {


    /**
     * 
     */
    @WebMethod(action = "logout")
    @RequestWrapper(localName = "logout", targetNamespace = "http://tolven.org/generator", className = "org.tolven.client.examples.ws.generator.jaxws.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://tolven.org/generator", className = "org.tolven.client.examples.ws.generator.jaxws.LogoutResponse")
    public void logout();

    /**
     * 
     * @param startYear
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "generateCCRXML")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "generateCCRXML", targetNamespace = "http://tolven.org/generator", className = "org.tolven.client.examples.ws.generator.jaxws.GenerateCCRXML")
    @ResponseWrapper(localName = "generateCCRXMLResponse", targetNamespace = "http://tolven.org/generator", className = "org.tolven.client.examples.ws.generator.jaxws.GenerateCCRXMLResponse")
    public String generateCCRXML(
        @WebParam(name = "startYear", targetNamespace = "")
        int startYear);

}
