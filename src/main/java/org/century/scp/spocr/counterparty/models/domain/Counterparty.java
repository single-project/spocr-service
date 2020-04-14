package org.century.scp.spocr.counterparty.models.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.person.models.domain.Person;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@DynamicInsert
@Table(name = "counterparties")
@NoArgsConstructor
public class Counterparty extends AbstractIdentifiedEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "legal_type_id")
  private Enumeration legalType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "counterparty_to_statuses",
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

  @OneToMany(
      mappedBy = "counterparty",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<PaymentDetails> paymentDetails;

  @Column(name = "no_vat")
  @ColumnDefault("true")
  private Boolean noVat;

  @Column(name = "active")
  private Boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "counterparty_contacts",
      joinColumns = @JoinColumn(name = "counterparty_id"),
      inverseJoinColumns = @JoinColumn(name = "contacts_id"))
  private Set<Contact> contacts;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "counterparty_person_id", referencedColumnName = "id")
  private Person personRekv;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "counterparty_legal_rekv_id", referencedColumnName = "id")
  private LegalRekv legalRekv;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "counterparty_to_ext_reg_system_props_id", referencedColumnName = "id")
  private ExtRegSystemCounterpartyProperties extRegSystemProperties;

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

  public boolean linkedWithPaymentDetails() {
    return paymentDetails != null && paymentDetails.size() > 0;
  }

  public void addPaymentType(Enumeration paymentType) {
    if (paymentTypes == null) {
      paymentTypes = new HashSet<>();
    }

    paymentTypes.add(paymentType);
  }

  public boolean linkedWithContacts() {
    return contacts != null && contacts.size() > 0;
  }

  public void addContact(Contact contact) {
    if (contacts == null) {
      contacts = new HashSet<>();
    }

    contacts.add(contact);
  }

  public void addPaymentDetails(PaymentDetails paymentDetails) {
    if (this.paymentDetails == null) {
      this.paymentDetails = new HashSet<>();
    }
    getPaymentDetails().add(paymentDetails);
    paymentDetails.setCounterparty(this);
  }

  public void removePaymentDetails(PaymentDetails paymentDetails) {
    getPaymentDetails().remove(paymentDetails);
    paymentDetails.setCounterparty(null);
  }

  public void setPaymentDetails(Set<PaymentDetails> paymentDetails) {
    if (linkedWithPaymentDetails()) {
      this.paymentDetails.removeIf(
          e -> {
            e.setCounterparty(null);
            return true;
          });
      if (paymentDetails != null) {
        paymentDetails.forEach(this::addPaymentDetails);
      }
    } else if (paymentDetails != null) {
      paymentDetails.forEach(this::addPaymentDetails);
    }
  }
}
