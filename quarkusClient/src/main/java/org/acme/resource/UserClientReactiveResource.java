package org.acme.resource;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.acme.model.User;
import org.acme.service.client.UserClient;
import org.acme.service.client.UserClientReactive;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user/reactive")
public class UserClientReactiveResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserClientReactiveResource.class);

  @RestClient
  UserClientReactive userClient;

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Response> getUser(
      @PathParam("id") String id) {

    return userClient.getUser(id)
        .invoke(user -> LOGGER.info("getUser: user=[{}]", user))
        .map(user -> Response.ok(user).build())
        .onFailure().invoke(throwable -> LOGGER.info("getUser:", throwable))
        .onFailure().recoverWithItem(() -> Response.status(Status.NOT_FOUND).build());
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Uni<String> addUser(
      User user) {

    return userClient.addUser(user);
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces
  public Uni<Void> updateUser(
      @PathParam("id") String id,
      User user) {

    return userClient.updateUser(id, user);
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces
  public Uni<Void> deleteUser(
      @PathParam("id") String id) {

    return userClient.deleteUser(id);
  }

}
