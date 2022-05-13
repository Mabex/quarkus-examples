package org.acme;

import io.smallrye.jwt.auth.principal.ParseException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.service.JWTService;

@Path("/hello")
public class GreetingResource {

    @Inject
    JWTService jwtService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws ParseException {
        String jwt = jwtService.buildJwt();
        jwtService.verifyJwt(jwt);
        return "Hello from RESTEasy Reactive";
    }
}
