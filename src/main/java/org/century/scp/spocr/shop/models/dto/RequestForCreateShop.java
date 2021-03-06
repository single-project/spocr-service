package org.century.scp.spocr.shop.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateShop extends ShopView {

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

  @NotBlank
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public BaseEntityListView getCounterparty() {
    return super.getCounterparty();
  }

  @NotNull
  @Override
  public AddressView getAddress() {
    return super.getAddress();
  }
}
