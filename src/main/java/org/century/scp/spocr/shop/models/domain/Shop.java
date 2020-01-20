package org.century.scp.spocr.shop.models.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.shoptype.models.domain.ShopType;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(name = "active")
  private Boolean active;

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
}
