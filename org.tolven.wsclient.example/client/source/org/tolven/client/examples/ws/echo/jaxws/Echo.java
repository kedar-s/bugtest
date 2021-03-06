
package org.tolven.client.examples.ws.echo.jaxws;

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
@WebService(name = "echo", targetNamespace = "http://tolven.org/echo")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Echo {


    /**
     * 
     * @param string
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "echo")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://tolven.org/echo", className = "org.tolven.client.examples.ws.echo.jaxws.Echo_Type")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://tolven.org/echo", className = "org.tolven.client.examples.ws.echo.jaxws.EchoResponse")
    public String echo(
        @WebParam(name = "string", targetNamespace = "")
        String string);

    /**
     * 
     */
    @WebMethod(action = "logout")
    @RequestWrapper(localName = "logout", targetNamespace = "http://tolven.org/echo", className = "org.tolven.client.examples.ws.echo.jaxws.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://tolven.org/echo", className = "org.tolven.client.examples.ws.echo.jaxws.LogoutResponse")
    public void logout();

}
