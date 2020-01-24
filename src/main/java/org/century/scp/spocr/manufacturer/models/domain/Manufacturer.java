package org.century.scp.spocr.manufacturer.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "manufactures")
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer extends BaseEntity<ManufacturerView> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  public Manufacturer(String name) {
    this.name = name;
    this.active = true;
  }

  public Manufacturer(Long id, String name, Boolean active, Long version) {
    super(version);
    this.id = id;
    this.name = name;
    this.active = active;
  }

}
