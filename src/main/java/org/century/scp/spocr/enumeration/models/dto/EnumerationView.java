package org.century.scp.spocr.enumeration.models.dto;

import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.DTO;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EnumerationView implements DTO {

  private Long id;
  private String name;
  private String ident;
  private Map<String, String> properties;

}
