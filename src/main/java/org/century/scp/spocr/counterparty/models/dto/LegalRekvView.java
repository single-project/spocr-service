package org.century.scp.spocr.counterparty.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalRekvView {

  private String shortName;
  private String fullName;
  private String inn;
  private String kpp;
  private String ogrn;
  private String ogrnDate;
  private String ogrnAuthority;
  private String okpo;
  private String okonh;
}
