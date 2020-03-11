package org.century.scp.spocr.shop.models.dto;

import java.util.LinkedHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.DTO;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtRegSystemShopPropertiesView implements DTO {

  ExtRegSystemView extRegSystem;
  LinkedHashMap properties;
  private Long id;

}
