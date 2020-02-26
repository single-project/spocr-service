package org.century.scp.spocr.person.models;

import org.century.scp.spocr.base.models.IdentifiedEnum;

public enum Citizenship implements IdentifiedEnum {
  RU("ru", "РОССИЯ");

  private String ident;
  private String description;

  Citizenship(String ident, String description) {
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
