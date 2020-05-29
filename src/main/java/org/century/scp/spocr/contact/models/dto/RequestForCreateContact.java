package org.century.scp.spocr.contact.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.person.models.dto.PersonView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateContact extends ContactView {

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
}
