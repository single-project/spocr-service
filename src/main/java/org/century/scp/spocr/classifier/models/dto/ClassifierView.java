package org.century.scp.spocr.classifier.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ClassifierView extends BaseEntityView {

  private String name;
  private BaseEntityListView manufacturer;

  public ClassifierView(
      Long id, String name, Long version, boolean active, BaseEntityListView manufacturer) {
    super(id, version, active);
    this.name = name;
    this.manufacturer = manufacturer;
  }

  public ClassifierView(String name, Long version, boolean active,
      BaseEntityListView manufacturer) {
    super(version, active);
    this.name = name;
    this.manufacturer = manufacturer;
  }


}
