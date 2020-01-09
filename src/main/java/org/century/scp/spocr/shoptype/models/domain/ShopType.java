package org.century.scp.spocr.shoptype.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.shop.models.domain.Shop;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "shop_types")
@NoArgsConstructor
@AllArgsConstructor
public class ShopType extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "manufactures_id", nullable = false)
  private Manufacturer manufacturer;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "shop_to_shop_types",
      joinColumns = @JoinColumn(name = "shop_types_id"),
      inverseJoinColumns = @JoinColumn(name = "shop_id"))
  private Set<Shop> shops;

  @Column(name = "active")
  private Boolean active;

  public ShopType(String name,
      Manufacturer manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.active = true;
  }

  @Override
  public String toString() {
    return "ShopType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", active=" + active +
        '}';
  }
}
