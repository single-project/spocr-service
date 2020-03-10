package org.century.scp.spocr.shop.models.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.specialization.domain.ShopSpecialization;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shops")
@NoArgsConstructor
public class Shop extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "gln")
  private String gln;

  @Column(name = "area")
  private Float area;

  @Column(name = "comment")
  private String comment;

  @Column(name = "signboard")
  private String signboard;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_id", nullable = false)
  private Counterparty counterparty;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_types",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_types_id"))
  private Set<ShopType> shopTypes;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_sales_channels",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "sales_channels_id"))
  private Set<SalesChannel> salesChannels;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_departs",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_departs_id"))
  private Set<ShopDepart> shopDeparts;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_specializations",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_specializations_id"))
  private Set<ShopSpecialization> shopSpecializations;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_contacts",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "contacts_id"))
  private Set<Contact> contacts;

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @Column(name = "active")
  private Boolean active;

  public Shop(
      Long id,
      String name,
      Counterparty counterparty,
      Collection<ShopType> shopTypes,
      Address address,
      Boolean active) {
    this.id = id;
    this.name = name;
    this.counterparty = counterparty;
    addShopTypes(shopTypes);
    this.address = address;
    this.active = active;
  }

  public Shop(
      String name, Counterparty counterparty) {
    this.name = name;
    this.counterparty = counterparty;
    this.active = true;
  }

  public void addShopTypes(@NonNull Collection<ShopType> shopTypes) {
    if (this.shopTypes == null) {
      this.shopTypes = new HashSet<>();
    }

    this.shopTypes.addAll(shopTypes);
  }


  public void addShopType(ShopType shopType) {
    if (shopTypes == null) {
      this.shopTypes = new HashSet<>();
    }
    shopTypes.add(shopType);
  }

  public void addSalesChannel(SalesChannel salesChannel) {
    if (salesChannels == null) {
      this.salesChannels = new HashSet<>();
    }
    salesChannels.add(salesChannel);
  }

  public void addShopDepart(ShopDepart shopDepart) {
    if (shopDeparts == null) {
      this.shopDeparts = new HashSet<>();
    }
    shopDeparts.add(shopDepart);
  }

  public void addShopSpecialization(ShopSpecialization shopSpecialization) {
    if (shopSpecializations == null) {
      this.shopSpecializations = new HashSet<>();
    }
    shopSpecializations.add(shopSpecialization);
  }

  public boolean linkedWithShopTypes() {
    return shopTypes != null && shopTypes.size() > 0;
  }

  public boolean linkedWithSalesChannels() {
    return salesChannels != null && salesChannels.size() > 0;
  }

  public boolean linkedWithShopDeparts() {
    return shopDeparts != null && shopDeparts.size() > 0;
  }

  public boolean linkedWithShopSpecializations() {
    return shopSpecializations != null && shopSpecializations.size() > 0;
  }

  public void setShopTypes(
      Set<ShopType> shopTypes) {
    this.shopTypes = shopTypes;
  }

  public void setSalesChannels(
      Set<SalesChannel> salesChannels) {
    this.salesChannels = salesChannels;
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
}
