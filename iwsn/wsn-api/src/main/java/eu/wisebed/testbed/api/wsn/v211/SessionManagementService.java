
package eu.wisebed.testbed.api.wsn.v211;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SessionManagementService", targetNamespace = "urn:SessionManagementService", wsdlLocation = "REPLACE_WITH_ACTUAL_URL")
public class SessionManagementService extends Service
{

    public SessionManagementService(URL wsdlLocation) {
        super(wsdlLocation, new QName("urn:SessionManagementService", "SessionManagementService"));
    }

    /**
     * 
     * @return
     *     returns SessionManagement
     */
    @WebEndpoint(name = "SessionManagementPort")
    public SessionManagement getSessionManagementPort() {
        return super.getPort(new QName("urn:SessionManagementService", "SessionManagementPort"), SessionManagement.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SessionManagement
     */
    @WebEndpoint(name = "SessionManagementPort")
    public SessionManagement getSessionManagementPort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:SessionManagementService", "SessionManagementPort"), SessionManagement.class, features);
    }

}
