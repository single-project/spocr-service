package org.century.scp.spocr.shoptype.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@EqualsAndHashCode(callSuper = true)
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

  @Column(name = "active")
  private Boolean active;

  public ShopType(Long id, String name, boolean active, long version, Manufacturer manufacturer) {
    super(version);
    this.id = id;
    this.name = name;
    this.active = active;
    this.manufacturer = manufacturer;
  }

  public ShopType(String name, Manufacturer manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.active = true;
  }

  @Override
  public String toString() {
    return "ShopType{" + "id=" + id + ", name='" + name + '\'' + ", active=" + active + '}';
  }
}
