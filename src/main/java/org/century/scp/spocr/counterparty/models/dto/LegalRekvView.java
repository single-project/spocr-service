package org.century.scp.spocr.counterparty.models.dto;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.DTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalRekvView implements DTO {

  private Long id;
  private String shortName;
  private String fullName;
  private String inn;
  private String kpp;
  private String ogrn;
  private String ogrnDate;
  private String ogrnAuthority;
  private String okpo;
  private String okonh;
  private HashMap suggestion;
}
