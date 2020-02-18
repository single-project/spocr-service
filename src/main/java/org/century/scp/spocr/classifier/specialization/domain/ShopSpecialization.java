package org.century.scp.spocr.classifier.specialization.domain;

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
@Table(name = "shop_specializations")
@NoArgsConstructor
public class ShopSpecialization extends Classifier {

  public ShopSpecialization(Long id, String name, boolean active, long version,
      Manufacturer manufacturer) {
    super(id, name, active, version, manufacturer);
  }

  public ShopSpecialization(String name, Manufacturer manufacturer) {
    super(name, manufacturer);
  }

  public ShopSpecialization(String name, Manufacturer manufacturer, boolean active) {
    super(name, manufacturer, active);
  }
}
