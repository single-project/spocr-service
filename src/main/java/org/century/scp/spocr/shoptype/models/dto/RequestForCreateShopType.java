package org.century.scp.spocr.shoptype.models.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

public class RequestForCreateShopType extends ShopTypeView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

  @Null
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @NotNull
  @NotEmpty
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public ManufacturerView getManufacturer() {
    return super.getManufacturer();
  }
}
