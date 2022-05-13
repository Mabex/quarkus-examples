package org.acme.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.model.User;
import org.acme.service.client.UserClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class UserClientResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserClientResource.class);

  @RestClient
  UserClient userClient;

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(
      @PathParam("id") String id) {

    LOGGER.info("getUser: start");
    return userClient.getUser(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public String addUser(
      User user) {

    LOGGER.info("addUser: start");
    return userClient.addUser(user);
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces
  public void updateUser(
      @PathParam("id") String id,
      User user) {

    LOGGER.info("updateUser: start");
    userClient.updateUser(id, user);
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces
  public void deleteUser(
      @PathParam("id") String id) {

    LOGGER.info("deleteUser: start");
    userClient.deleteUser(id);
  }

}
