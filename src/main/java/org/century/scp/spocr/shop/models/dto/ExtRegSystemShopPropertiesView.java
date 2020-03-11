package org.century.scp.spocr.shop.models.dto;

import java.util.LinkedHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.DTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtRegSystemShopPropertiesView implements DTO {

  BaseEntityListView extRegSystem;
  LinkedHashMap properties;
  private Long id;
}
