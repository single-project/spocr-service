package org.century.scp.spocr.person.models;

import org.century.scp.spocr.base.models.IdentifiedEnum;

public enum DocType implements IdentifiedEnum {
  PASSPORT_RF("pass_n_rf", "Паспорт гражданина РФ");

  private String ident;
  private String description;

  DocType(String ident, String description) {
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
