package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.beans.MyApplicationScopedBean;
import org.acme.beans.MyRequestScopedBean;
import org.acme.beans.MySingletonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    MyApplicationScopedBean myApplicationScopedBean;

    @Inject
    MySingletonBean mySingletonBean;

    @Inject
    MyRequestScopedBean myRequestScopedBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("/beans")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String beans() {
        myApplicationScopedBean.test();
        mySingletonBean.test();
        myRequestScopedBean.test();
        return "Hello from RESTEasy Reactive";
    }

    @Path("/no-injection")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String noInjection() {
        NoInjectionBean bean = new NoInjectionBean();
        LOGGER.info("noInjection: [{}]", bean.getProp());
        return bean.getProp();
    }
}
