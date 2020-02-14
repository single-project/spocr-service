package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {

  private String name;
  private AddressView address;
  private List<ClassifierView> shopTypes;
  private List<ClassifierView> salesChannels;
  private CounterpartyView counterparty;

  public ShopView(
      String name,
      boolean active,
      AddressView address,
      List<ClassifierView> shopTypes,
      List<ClassifierView> salesChannels,
      CounterpartyView counterparty) {
    super(active);
    this.name = name;
    this.shopTypes = shopTypes;
    this.salesChannels = salesChannels;
    this.counterparty = counterparty;
    this.address = address;
  }
}
