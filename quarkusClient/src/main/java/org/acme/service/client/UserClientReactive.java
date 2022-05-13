package org.acme.service.client;

import io.smallrye.mutiny.Uni;
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
import org.acme.model.User;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/user")
@RegisterRestClient
public interface UserClientReactive {

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Uni<User> getUser(
      @PathParam("id") String id);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  Uni<String> addUser(
      User user);

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.WILDCARD)
  Uni<Void> updateUser(
      @PathParam("id") String id,
      User user);

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.WILDCARD)
  Uni<Void> deleteUser(
      @PathParam("id") String id);

}
