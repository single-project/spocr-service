package org.century.scp.spocr.enumeration.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class RequestForCreateEnumeration extends EnumerationView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotBlank
  @Override
  public String getName() {
    return super.getName();
  }

  @NotBlank
  @Override
  public String getIdent() {
    return super.getIdent();
  }
}
