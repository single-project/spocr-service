package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {

  private String name;
  private List<ShopTypeView> shopTypes;
  private AddressView address;
  private CounterpartyView counterparty;

  public ShopView(
      Long id,
      String name,
      Long version,
      boolean active,
      List<ShopTypeView> shopTypes,
      CounterpartyView counterparty) {
    super(id, version, active);
    this.name = name;
    this.shopTypes = shopTypes;
    this.counterparty = counterparty;
  }
}
