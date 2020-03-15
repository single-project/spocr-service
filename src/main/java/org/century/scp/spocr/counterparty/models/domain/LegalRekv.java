package org.century.scp.spocr.counterparty.models.domain;

import java.util.LinkedHashMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.LinkedHashMapConverter;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "legal_rekvs")
@NoArgsConstructor
@AllArgsConstructor
public class LegalRekv implements IdentifiedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
  @Convert(converter = LinkedHashMapConverter.class)
  private LinkedHashMap suggestion;

}
