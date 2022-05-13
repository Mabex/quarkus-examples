package org.acme.model;

import java.util.Objects;

public class User {

  private String name;
  private String surname;

  public User() {
  }

  public User(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return name.equals(user.name) && surname.equals(user.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname);
  }
}
