package org.century.scp.spocr.person.models.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdatePerson extends PersonView {

  private List<String> updatedFields;

  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotBlank
  @Override
  public String getName() {
    return super.getName();
  }

  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
