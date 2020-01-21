package org.century.scp.spocr.shoptype.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.shoptype.models.domain.ShopType;

@Getter
@Setter
@NoArgsConstructor
public class ShopTypeView extends BaseEntityView {

  private ManufacturerView manufacturer;

  public ShopTypeView(ShopType entity) {
    super(entity);
    this.manufacturer = entity.getManufacturer().map();
  }
}
