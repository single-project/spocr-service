package org.century.scp.spocr.shop.models.dto;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.contact.models.dto.ContactView;

@Getter
@Setter
@NoArgsConstructor
public class ShopView extends BaseEntityView {

  private String name;
  private AddressView address;
  private Set<ClassifierView> shopTypes;
  private Set<ClassifierView> salesChannels;
  private Set<ClassifierView> shopDeparts;
  private Set<ClassifierView> shopSpecializations;
  private Set<BaseEntityListView> manufacturers;
  private BaseEntityListView counterparty;
  private String gln;
  private Float area;
  private String comment;
  private String signboard;
  private Set<ContactView> contacts;
  private ExtRegSystemShopPropertiesView extRegSystemProperties;

}
