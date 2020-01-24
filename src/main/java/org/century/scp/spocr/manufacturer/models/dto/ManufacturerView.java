package org.century.scp.spocr.manufacturer.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerView extends BaseEntityView {

  public ManufacturerView(String name) {
    super(name);
  }

  public ManufacturerView(long id, String name, Long version, boolean active) {
    super(id, name, version, active);
  }
}
