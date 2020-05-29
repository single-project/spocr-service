package org.century.scp.spocr.counterparty.models.domain;

import java.util.LinkedHashMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.converters.MapConverter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;

@Getter
@Setter
@Entity
@Table(name = "legal_rekvs")
@NoArgsConstructor
@AllArgsConstructor
public class LegalRekv extends AbstractIdentifiedEntity {

  @Column(name = "short_name")
  private String shortName;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "inn")
  private String inn;

  @Column(name = "kpp")
  private String kpp;

  @Column(name = "ogrn")
  private String ogrn;

  @Column(name = "ogrn_date")
  private String ogrnDate;

  @Column(name = "ogrn_authority")
  private String ogrnAuthority;

  @Column(name = "okpo")
  private String okpo;

  @Column(name = "okonh")
  private String okonh;

  @Column(name = "suggestion")
  @Convert(converter = MapConverter.class)
  private LinkedHashMap suggestion;
}
