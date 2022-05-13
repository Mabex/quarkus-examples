package org.acme.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class MyRequestScopedBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyRequestScopedBean.class);

  @PostConstruct
  void init() {
    LOGGER.info("MyRequestScopedBean: initialization");
  }

  public String test() {
    return "ok";
  }
}
