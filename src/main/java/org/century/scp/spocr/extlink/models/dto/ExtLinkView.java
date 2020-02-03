package org.century.scp.spocr.extlink.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.extlink.models.EntityType;

@Data
@NoArgsConstructor
public class ExtLinkView {

  private Long id;
  private int entityId;
  private int entityExtId;
  private EntityType entityType;
  private int extProgId;

}
