package org.century.scp.spocr.legaltype.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateLegalType extends LegalTypeView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

  @Null
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @Override
  public String getOpfShort() {
    return super.getOpfShort();
  }

  @Override
  public String getOpfFull() {
    return super.getOpfFull();
  }

  @Override
  public String getOpfCode() {
    return super.getOpfCode();
  }

  @Override
  public String getOpfType() {
    return super.getOpfType();
  }
}
