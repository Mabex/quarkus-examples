package org.acme.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UserResourceTest {

  @Test
  void testHelloEndpoint() {
    given()
        .when().post("/user")
        .then()
        .statusCode(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
  }

}
