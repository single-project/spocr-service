package org.century.scp.spocr.shop.models.dto;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {

  private String name;
  private AddressView address;
  private Set<ClassifierView> shopTypes;
  private Set<ClassifierView> salesChannels;
  private CounterpartyView counterparty;
  private String gln;
  private Float area;
  private String comment;
  private String signboard;
  private Set<ContactView> contacts;

  public ShopView(
      String name,
      boolean active,
      AddressView address,
      Set<ClassifierView> shopTypes,
      Set<ClassifierView> salesChannels,
      CounterpartyView counterparty) {
    super(active);
    this.name = name;
    this.shopTypes = shopTypes;
    this.salesChannels = salesChannels;
    this.counterparty = counterparty;
    this.address = address;
  }
}
