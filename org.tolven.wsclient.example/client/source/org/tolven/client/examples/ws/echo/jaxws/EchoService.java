
package org.tolven.client.examples.ws.echo.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.5-b05 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EchoService", targetNamespace = "http://tolven.org/echo", wsdlLocation = "http://dev.able.com:8080/ws/echo?wsdl")
public class EchoService
    extends Service
{

    private final static URL ECHOSERVICE_WSDL_LOCATION;
    private final static WebServiceException ECHOSERVICE_EXCEPTION;
    private final static QName ECHOSERVICE_QNAME = new QName("http://tolven.org/echo", "EchoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://dev.able.com:8080/ws/echo?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ECHOSERVICE_WSDL_LOCATION = url;
        ECHOSERVICE_EXCEPTION = e;
    }

    public EchoService() {
        super(__getWsdlLocation(), ECHOSERVICE_QNAME);
    }

    public EchoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ECHOSERVICE_QNAME, features);
    }

    public EchoService(URL wsdlLocation) {
        super(wsdlLocation, ECHOSERVICE_QNAME);
    }

    public EchoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ECHOSERVICE_QNAME, features);
    }

    public EchoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EchoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Echo
     */
    @WebEndpoint(name = "echoPort")
    public Echo getEchoPort() {
        return super.getPort(new QName("http://tolven.org/echo", "echoPort"), Echo.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Echo
     */
    @WebEndpoint(name = "echoPort")
    public Echo getEchoPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tolven.org/echo", "echoPort"), Echo.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ECHOSERVICE_EXCEPTION!= null) {
            throw ECHOSERVICE_EXCEPTION;
        }
        return ECHOSERVICE_WSDL_LOCATION;
    }

}
