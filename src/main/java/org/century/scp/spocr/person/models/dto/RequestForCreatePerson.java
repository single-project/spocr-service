package org.century.scp.spocr.person.models.dto;

import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreatePerson extends PersonView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

}
