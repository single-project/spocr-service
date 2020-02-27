package org.century.scp.spocr.owner.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class OwnerView extends BaseEntityView {

  private String name;

}
