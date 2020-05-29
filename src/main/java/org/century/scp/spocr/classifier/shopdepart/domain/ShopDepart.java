package org.century.scp.spocr.classifier.shopdepart.domain;

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
@Table(name = "shop_departs")
@NoArgsConstructor
public class ShopDepart extends Classifier {

  public ShopDepart(String name, Manufacturer manufacturer, boolean active) {
    super(name, manufacturer, active);
  }
}
