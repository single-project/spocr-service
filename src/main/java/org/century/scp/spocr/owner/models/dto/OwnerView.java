package org.century.scp.spocr.owner.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.contact.models.dto.ContactView;

@Getter
@Setter
@NoArgsConstructor
public class OwnerView extends BaseEntityView {

  private String name;
  private List<ContactView> contacts;

}
