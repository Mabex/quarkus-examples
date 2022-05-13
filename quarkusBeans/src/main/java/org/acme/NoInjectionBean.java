package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class NoInjectionBean {

  @ConfigProperty(name = "my.prop")
  String prop;

  public String getProp() {
    return prop;
  }
}
