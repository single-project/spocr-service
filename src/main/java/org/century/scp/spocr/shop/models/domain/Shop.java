package org.century.scp.spocr.shop.models.domain;

import java.util.ArrayList;
import java.util.List;
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
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = false)
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

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_id", nullable = false)
  private Counterparty counterparty;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_types",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_types_id"))
  private List<ShopType> shopTypes;

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @Column(name = "active")
  private Boolean active;


  public Shop(Long id, String name,
      Counterparty counterparty,
      List<ShopType> shopTypes, Address address, Boolean active) {
    this.id = id;
    this.name = name;
    this.counterparty = counterparty;
    this.shopTypes = shopTypes;
    this.address = address;
    this.active = active;
  }

  public Shop(
      Long id,
      String name,
      Boolean active,
      Long version,
      Counterparty counterparty,
      List<ShopType> shopTypes) {
    super(version);
    this.id = id;
    this.name = name;
    this.active = active;
    this.shopTypes = shopTypes;
    this.counterparty = counterparty;
  }

  public Shop(String name, Counterparty counterparty, ShopType shopType) {
    this.name = name;
    this.counterparty = counterparty;
    this.shopTypes = new ArrayList<>();
    this.shopTypes.add(shopType);
    this.active = true;
  }

  public void addShopType(ShopType shopType) {
    shopTypes.add(shopType);
  }

  public boolean linkedWithShopTypes() {
    return shopTypes != null && shopTypes.size() > 0;
  }
}
