package org.century.scp.spocr.classifier.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class ClassifierView extends BaseEntityView {
  private String name;
  private BaseEntityListView manufacturer;

}
