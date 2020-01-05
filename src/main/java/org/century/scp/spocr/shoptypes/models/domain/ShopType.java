package org.century.scp.spocr.shoptypes.models.domain;

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
import org.century.scp.spocr.events.models.domain.AbstractAuditableEntity;
import org.century.scp.spocr.manufactures.models.domain.Manufacturer;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "shop_types")
@NoArgsConstructor
@AllArgsConstructor
public class ShopType extends AbstractAuditableEntity {

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

  public ShopType(String name,
      Manufacturer manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.active = true;
  }
}
