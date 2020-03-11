package org.century.scp.spocr.classifier.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateClassifier extends ClassifierView {

  private List<String> updatedFields;

  @NotNull
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotNull
  @PositiveOrZero
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @NotNull
  @NotEmpty
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public BaseEntityListView getManufacturer() {
    return super.getManufacturer();
  }

  @NotNull
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return this.updatedFields;
  }
}
