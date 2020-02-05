package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;

public class RequestForCreateShop extends ShopView {

  public RequestForCreateShop(
      Long id,
      String name,
      Long version,
      boolean active,
      List<ShopTypeView> shopTypes,
      CounterpartyView counterparty,
      AddressView address) {
    super(id, name, version, active, address, shopTypes, counterparty);
  }

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
  public CounterpartyView getCounterparty() {
    return super.getCounterparty();
  }

  @NotNull
  @Override
  public AddressView getAddress() {
    return super.getAddress();
  }
}