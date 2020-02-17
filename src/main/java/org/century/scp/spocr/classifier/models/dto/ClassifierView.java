package org.century.scp.spocr.classifier.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ClassifierView extends BaseEntityView {

  private String name;
  private ManufacturerView manufacturer;

  public ClassifierView(
      Long id, String name, Long version, boolean active, ManufacturerView manufacturer) {
    super(id, version, active);
    this.name = name;
    this.manufacturer = manufacturer;
  }

  public ClassifierView(String name, Long version, boolean active, ManufacturerView manufacturer) {
    super(version, active);
    this.name = name;
    this.manufacturer = manufacturer;
  }


}
