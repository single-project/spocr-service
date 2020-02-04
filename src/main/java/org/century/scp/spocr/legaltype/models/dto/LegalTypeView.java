package org.century.scp.spocr.legaltype.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class LegalTypeView extends BaseEntityView {

  private String name;
  private Boolean active;
}
