package org.century.scp.spocr.contact.models.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.person.models.dto.PersonView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateContact extends ContactView {

  private List<String> updatedFields;

  @NotNull
  @Override
  public Long getId() {
    return super.getId();
  }

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
  @Override
  public ContactRoleView getRole() {
    return super.getRole();
  }

  @NotNull
  @Override
  public PersonView getPerson() {
    return super.getPerson();
  }

  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return this.updatedFields;
  }
}
