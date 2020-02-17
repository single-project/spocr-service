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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.base.annotations.OnCreateUpdatedAttach;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shops")
@NamedEntityGraph(
    name = "Shop.withClassifier",
    attributeNodes = {@NamedAttributeNode("shopTypes"), @NamedAttributeNode("salesChannels")})
@NoArgsConstructor
public class Shop extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_id", nullable = false)
  @OnCreateUpdatedAttach
  private Counterparty counterparty;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_types",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_types_id"))
  @OnCreateUpdatedAttach
  private Set<ShopType> shopTypes;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_sales_channel",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "sales_channel_id"))
  @OnCreateUpdatedAttach
  private Set<SalesChannel> salesChannels;

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
      String name, Counterparty counterparty, ShopType shopType, SalesChannel salesChannel) {
    this.name = name;
    this.counterparty = counterparty;
    addShopType(shopType);
    addSalesChannel(salesChannel);
    this.active = true;
  }

  public void addShopTypes(@NonNull Collection<ShopType> shopTypes) {
    if (this.shopTypes == null) {
      this.shopTypes = new HashSet<>();
    }

    this.shopTypes.addAll(shopTypes);
  }

  public void addSalesChannels(@NonNull Collection<SalesChannel> salesChannels) {
    if (this.salesChannels == null) {
      this.salesChannels = new HashSet<>();
    }

    this.salesChannels.addAll(salesChannels);
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

  public boolean linkedWithShopTypes() {
    return shopTypes != null && shopTypes.size() > 0;
  }

  public boolean linkedWithSalesChannel() {
    return salesChannels != null && salesChannels.size() > 0;
  }

  public void setShopTypes(
      Set<ShopType> shopTypes) {
    this.shopTypes = shopTypes;
  }

  public void setSalesChannels(
      Set<SalesChannel> salesChannels) {
    this.salesChannels = salesChannels;
  }
}
