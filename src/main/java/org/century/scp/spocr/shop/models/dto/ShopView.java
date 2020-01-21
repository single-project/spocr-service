package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {
  private CounterpartyView counterparty;
  private List<ShopTypeView> shopTypes;

  public ShopView(Shop shop) {
    super(shop);
    this.counterparty = shop.getCounterparty().map();
    this.shopTypes = shop.getShopTypes().stream().map(ShopType::map).collect(Collectors.toList());
  }
}
