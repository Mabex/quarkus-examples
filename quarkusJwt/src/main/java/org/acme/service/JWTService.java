package org.acme.service;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class JWTService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JWTService.class);

  @Inject
  JWTParser jwtParser;

  public String buildJwt() {
    return Jwt
        .audience("myAudience")
        .issuer("Quarkus")
        .claim("myClaim", "myValue")
        .subject("subject")
        .signWithSecret("AyM1SysPpbyDfgZld3umj1qzKObwVMko");
  }

  public void verifyJwt(String jwt) throws ParseException {
    JsonWebToken jsonWebToken = jwtParser.verify(jwt, "AyM1SysPpbyDfgZld3umj1qzKObwVMko");
    LOGGER.info("{}", jsonWebToken.getAudience());
    LOGGER.info("{}", jsonWebToken.getIssuer());
    LOGGER.info("{}", (Object) jsonWebToken.getClaim("myClaim"));
    LOGGER.info("{}", jsonWebToken);
  }

}
