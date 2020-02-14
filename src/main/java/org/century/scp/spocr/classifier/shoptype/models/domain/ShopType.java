package org.century.scp.spocr.classifier.shoptype.models.domain;

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
@Table(name = "shop_types")
@NoArgsConstructor
public class ShopType extends Classifier {

  public ShopType(Long id, String name, boolean active, long version, Manufacturer manufacturer) {
    super(id, name, active, version, manufacturer);
  }

  public ShopType(String name, Manufacturer manufacturer) {
    super(name, manufacturer);
  }

  public ShopType(String name, Manufacturer manufacturer, boolean active) {
    super(name, manufacturer, active);
  }
}
