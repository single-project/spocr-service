package org.century.scp.spocr.classifier.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateClassifier extends ClassifierView {

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
  public Boolean getActive() {
    return super.getActive();
  }

  @NotBlank
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public BaseEntityListView getManufacturer() {
    return super.getManufacturer();
  }
}
