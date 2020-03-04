package org.century.scp.spocr.counterparty.models.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.LinkedHashMapConverter;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.person.models.domain.Person;
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "counterparty_to_counterparty_statuses",
      joinColumns = @JoinColumn(name = "counterparty_id"),
      inverseJoinColumns = @JoinColumn(name = "enumerations_id"))
  private Set<Enumeration> statuses;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "counterparty_to_payment_types",
      joinColumns = @JoinColumn(name = "counterparty_id"),
      inverseJoinColumns = @JoinColumn(name = "enumerations_id"))
  private Set<Enumeration> paymentTypes;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id")
  private Counterparty parent;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "legal_type_id")
  private Enumeration legalType;

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
  @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "counterparty_payment_details_id", referencedColumnName = "id")
  private PaymentDetails paymentDetails;

  @Column(name = "no_vat")
  private Boolean noVat;

  @Column(name = "active")
  private Boolean active;

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "counterparty_person_id", referencedColumnName = "id")
  private Person personRekv;

  @Column(name = "suggestion")
  @Convert(converter = LinkedHashMapConverter.class)
  private LinkedHashMap suggestion;

  @Column
  private String comment;

  public Counterparty(String name) {
    this.name = name;
    this.active = true;
  }

  public boolean linkedWithStatuses() {
    return statuses != null && statuses.size() > 0;
  }

  public void addStatus(Enumeration status) {
    if (statuses == null) {
      statuses = new HashSet<>();
    }

    statuses.add(status);
  }

  public boolean linkedWithPaymentTypes() {
    return paymentTypes != null && paymentTypes.size() > 0;
  }

  public void addPaymentType(Enumeration paymentType) {
    if (paymentTypes == null) {
      paymentTypes = new HashSet<>();
    }

    paymentTypes.add(paymentType);
  }
}
