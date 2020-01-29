package org.century.scp.spocr.shoptype.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

@Getter
@Setter
@NoArgsConstructor
public class ShopTypeView extends BaseEntityView {

  private String name;

  private ManufacturerView manufacturer;

  public ShopTypeView(Long id, String name, Long version, boolean active,
      ManufacturerView manufacturer) {
    super(id, version, active);
    this.name = name;
    this.manufacturer = manufacturer;
  }

}
