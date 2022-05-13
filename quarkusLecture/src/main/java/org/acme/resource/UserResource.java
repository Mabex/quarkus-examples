package org.acme.resource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Path("/api/v1/user")
@Path("/user")
@Produces
public class UserResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

  private final Map<String, User> users = Collections.synchronizedMap(new LinkedHashMap<>());

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response addUser(User user) { // C
    LOGGER.info("addUser: start - user=[{}]", user);

    if (user.getName() == null || user.getName().isBlank() ||
        user.getSurname() == null || user.getSurname().isBlank()) {

      return Response.status(Status.BAD_REQUEST).build();
    }

    boolean matched = users.entrySet().stream()
        .anyMatch(stringUserEntry -> stringUserEntry.getValue().equals(user));
    if (matched) {
      return Response.status(Status.CONFLICT).build();
    }

    user.setId(UUID.randomUUID().toString());
    users.put(user.getId(), user);

    LOGGER.info("addUser: stop");
    return Response.ok(user.getId()).build();
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser(
      @PathParam("id") String id) { // R

    LOGGER.info("getUser: start - id=[{}]", id);

    User user = users.get(id);
    if (user == null) {
      return Response.status(Status.NOT_FOUND).build();
    }

    LOGGER.info("getUser: stop");
    return Response.ok(user).build();
  }

  @Path("/{id}")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(
      User user,
      @PathParam("id") String id) { // U

    LOGGER.info("updateUser: start - id=[{}], user=[{}]", id, user);

    if (user.getName() == null || user.getName().isBlank() ||
        user.getSurname() == null || user.getSurname().isBlank()) {

      return Response.status(Status.BAD_REQUEST).build();
    }

    if (!users.containsKey(id)) {
      return Response.status(Status.NOT_FOUND).build();
    }

    user.setId(id);
    users.put(id, user);

    LOGGER.info("updateUser: stop");
    return Response.ok().build();
  }

  @Path("/{id}")
  @DELETE
  public Response deleteUser(
      @PathParam("id") String id) { // D
    LOGGER.info("deleteUser: start - id=[{}]", id);

    if (!users.containsKey(id)) {
      return Response.status(Status.NOT_FOUND).build();
    }

    users.remove(id);

    LOGGER.info("deleteUser: stop");
    return Response.ok().build();
  }

}
