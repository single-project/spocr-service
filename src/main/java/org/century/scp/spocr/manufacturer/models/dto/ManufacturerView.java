package org.century.scp.spocr.manufacturer.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerView extends BaseEntityView {

  public ManufacturerView(Manufacturer entity) {
    super(entity);
  }
}
