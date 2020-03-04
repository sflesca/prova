package org.psw.rest;

import org.psw.ejb.ClienteManagerBean;
import org.psw.model.Cliente;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import java.util.List;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class ciccino {

    @EJB
    ClienteManagerBean bb;
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    public List<Cliente> getClichedMessage() {
        // Return some cliched textual content
        return bb.getClienti();
    }
}
