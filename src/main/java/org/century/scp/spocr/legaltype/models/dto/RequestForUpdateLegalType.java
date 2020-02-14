package org.century.scp.spocr.legaltype.models.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateLegalType extends LegalTypeView {

  @NotNull
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotNull
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
}
