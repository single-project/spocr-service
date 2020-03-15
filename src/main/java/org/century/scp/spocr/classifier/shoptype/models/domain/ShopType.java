package org.century.scp.spocr.classifier.shoptype.models.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.classifier.domain.Classifier;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@Entity
@Getter
@Setter
@Table(name = "shop_types")
@NoArgsConstructor
public class ShopType extends Classifier {

  public ShopType(String name, Manufacturer manufacturer, boolean active) {
    super(name, manufacturer, active);
  }
}
