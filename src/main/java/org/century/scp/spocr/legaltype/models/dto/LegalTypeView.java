package org.century.scp.spocr.legaltype.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalTypeView extends BaseEntityView {

  private Long id;
  private String name;
  private Boolean active;

  private String okfpShortName;
  private String okfpFullName;
  private String okpfId;
  private String okpfType;
}
