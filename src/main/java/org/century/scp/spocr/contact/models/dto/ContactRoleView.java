package org.century.scp.spocr.contact.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRoleView extends BaseEntityView {

  private String name;
}
