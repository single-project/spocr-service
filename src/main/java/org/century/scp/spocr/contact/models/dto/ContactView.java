package org.century.scp.spocr.contact.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.person.models.dto.PersonView;

@Getter
@Setter
@NoArgsConstructor
public class ContactView extends BaseEntityView {
  private ContactRoleView role;
  private PersonView person;
  private String comment;
}
