package org.century.scp.spocr.counterparty.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shoptype.models.domain.ShopType;

@Getter
@Setter
@NoArgsConstructor
public class CounterpartyView extends BaseEntityView {

  public CounterpartyView(Counterparty counterparty) {
    super(counterparty);
  }

  public CounterpartyView(long id, String name, long version, boolean active) {
    super(id, name, version, active);
  }
}
