package org.acme.beans;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MySingletonBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(MySingletonBean.class);

  @PostConstruct
  void init() {
    LOGGER.info("MySingletonBean: initialization");
  }

  public String test() {
    return "ok";
  }
}
