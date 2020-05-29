package org.century.scp.spocr.draft.models.dto;

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
public class DraftView implements DTO {

  private Long id;
  private String entity;
  private Map<String, String> body;

}
