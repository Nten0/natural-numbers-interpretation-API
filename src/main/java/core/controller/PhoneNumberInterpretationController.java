package core.controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/number")
public class PhoneNumberInterpretationController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationController.class);

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String number) {

        return Response.status(200).entity(number).build();
    }

}