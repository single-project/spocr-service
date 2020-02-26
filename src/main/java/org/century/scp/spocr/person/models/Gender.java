package org.century.scp.spocr.person.models;

import org.century.scp.spocr.base.models.IdentifiedEnum;

public enum Gender implements IdentifiedEnum {
  MALE("m", "Мужской"),
  FEMALE("fm", "Женский");

  private String ident;
  private String description;

  Gender(String ident, String description) {
    this.ident = ident;
    this.description = description;
  }

  public String getIdent() {
    return ident;
  }

  public String getDescription() {
    return description;
  }

}
