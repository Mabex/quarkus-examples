package org.acme.resource;

import io.vertx.core.http.HttpServerRequest;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @ConfigProperty(name = "my.custom.property")
    String myCustomString;

    @ConfigProperty(name = "another.custom.property", defaultValue = "myDefaultValue")
    String anotherCustomString;

    @ConfigProperty(name = "my.custom.property")
    Optional<String> myCustomOptionalString;

    @ConfigProperty(name = "my.custom.property")
    boolean myCustomBoolean;

    @Context UriInfo uriInfo;
    @Context HttpHeaders httpHeaders;
    @Context SecurityContext securityContext;
    @Context HttpServerRequest request;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/props")
    @Produces(MediaType.TEXT_PLAIN)
    public String props() {
        return String.join(" ", myCustomString, anotherCustomString,
            myCustomOptionalString.orElse(null), String.valueOf(myCustomBoolean));
    }

    @GET
    @Path("/query")
    @Produces(MediaType.TEXT_PLAIN)
    public String query(@QueryParam("paramName") String param) {
        LOGGER.info("query: start - param=[{}]", param);
        LOGGER.info("query: stop");
        return param;
    }

    @GET
    @Path("/header")
    @Produces(MediaType.TEXT_PLAIN)
    public String header(@HeaderParam("paramName") String param) {
        LOGGER.info("header: start - param=[{}]", param);
        LOGGER.info("header: stop");
        return param;
    }

    @GET
    @Path("/form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String form(@FormParam("paramName") String param) {
        LOGGER.info("form: start - param=[{}]", param);
        LOGGER.info("form: stop");
        return param;
    }

    @GET
    @Path("/cookie")
    @Produces(MediaType.TEXT_PLAIN)
    public String cookie(@CookieParam("paramName") String param) {
        LOGGER.info("cookie: start - param=[{}]", param);
        LOGGER.info("cookie: stop");
        return param;
    }

    @GET
    @Path("/context")
    @Produces(MediaType.TEXT_PLAIN)
    public String context() {

        LOGGER.info("context: start - uriInfo.getPath=[{}]", uriInfo.getPath());
        LOGGER.info("context: httpHeaders=[{}]", httpHeaders.getRequestHeaders());
        LOGGER.info("context: isSecure=[{}]", securityContext.isSecure());
        LOGGER.info("context: host=[{}]", request.host());
        LOGGER.info("context: stop");
        return "ok";
    }
}
