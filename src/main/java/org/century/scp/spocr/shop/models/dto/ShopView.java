package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {

  private List<ShopTypeView> shopTypes;
  private CounterpartyView counterparty;

  public ShopView(String name, CounterpartyView counterparty, List<ShopTypeView> shopTypes) {
    super(name);
    this.counterparty = counterparty;
    this.shopTypes = shopTypes;
  }

  public ShopView(
      Long id,
      String name,
      Long version,
      boolean active,
      List<ShopTypeView> shopTypes,
      CounterpartyView counterparty) {
    super(id, name, version, active);
    this.shopTypes = shopTypes;
    this.counterparty = counterparty;
  }
}
