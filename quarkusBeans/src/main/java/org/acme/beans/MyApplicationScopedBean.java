package org.acme.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MyApplicationScopedBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationScopedBean.class);

  @PostConstruct
  void init() {
    LOGGER.info("MyApplicationScopedBean: initialization");
  }

  public String test() {
    return "ok";
  }
}
