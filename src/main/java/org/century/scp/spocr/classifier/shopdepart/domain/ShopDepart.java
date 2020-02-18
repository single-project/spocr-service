package org.century.scp.spocr.classifier.shopdepart.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.classifier.domain.Classifier;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shop_departs")
@NoArgsConstructor
public class ShopDepart extends Classifier {

  public ShopDepart(Long id, String name, boolean active, long version, Manufacturer manufacturer) {
    super(id, name, active, version, manufacturer);
  }

  public ShopDepart(String name, Manufacturer manufacturer) {
    super(name, manufacturer);
  }

  public ShopDepart(String name, Manufacturer manufacturer, boolean active) {
    super(name, manufacturer, active);
  }
}
