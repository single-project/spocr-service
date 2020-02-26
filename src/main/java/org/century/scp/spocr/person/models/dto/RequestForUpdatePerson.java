package org.century.scp.spocr.person.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdatePerson extends PersonView {

  private List<String> updatedFields;

  @NotNull
  @Positive
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
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
