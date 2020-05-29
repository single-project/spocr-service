package org.century.scp.spocr.manufacturer.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;

@Entity
@Getter
@Setter
@Table(name = "manufacturers")
@NoArgsConstructor
public class Manufacturer extends AbstractIdentifiedEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  public Manufacturer(String name) {
    this.name = name;
    this.active = true;
  }

  public Manufacturer(String name, boolean active) {
    this.name = name;
    this.active = active;
  }

}
