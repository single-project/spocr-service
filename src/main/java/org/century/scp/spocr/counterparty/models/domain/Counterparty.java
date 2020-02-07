package org.century.scp.spocr.counterparty.models.domain;

import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.SuggestionConverter;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "counterparties")
@NoArgsConstructor
@AllArgsConstructor
public class Counterparty extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "legal_type_id")
  private LegalType legalType;

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

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "counterparty_payment_details_id", referencedColumnName = "id")
  private PaymentDetails paymentDetails;

  @Column(name = "active")
  private Boolean active;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id")
  private Counterparty parent;

  @Column(name = "suggestion")
  @Convert(converter = SuggestionConverter.class)
  private LinkedHashMap suggestion;

  public Counterparty(String name) {
    this.name = name;
    this.active = true;
  }

  public Counterparty(
      Long id,
      String name,
      PaymentDetails paymentDetails,
      Boolean active,
      LinkedHashMap suggestion) {
    this.id = id;
    this.name = name;
    this.paymentDetails = paymentDetails;
    this.active = active;
    this.suggestion = suggestion;
  }

  @Builder
  public Counterparty(
      Long id,
      Long version,
      String name,
      LegalType legalType,
      String fullName,
      String inn,
      String kpp,
      String ogrn,
      String ogrnDate,
      String ogrnAuthority,
      String okpo,
      String okonh,
      PaymentDetails paymentDetails,
      Boolean active,
      LinkedHashMap suggestion) {
    super(version);
    this.id = id;
    this.name = name;
    this.legalType = legalType;
    this.fullName = fullName;
    this.inn = inn;
    this.kpp = kpp;
    this.ogrn = ogrn;
    this.ogrnDate = ogrnDate;
    this.ogrnAuthority = ogrnAuthority;
    this.okpo = okpo;
    this.okonh = okonh;
    this.paymentDetails = paymentDetails;
    this.active = active;
    this.suggestion = suggestion;
  }

  @Override
  public List<String> getUpdatedFields() {
    return super.getUpdatedFields();
  }
}
