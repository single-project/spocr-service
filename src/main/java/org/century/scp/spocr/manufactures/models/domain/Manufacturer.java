package org.century.scp.spocr.manufactures.models.domain;

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
import org.century.scp.spocr.events.models.domain.AbstractAuditableEntity;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "manufactures")
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer extends AbstractAuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  public Manufacturer(String name, Boolean active) {
    this.name = name;
    this.active = active;
  }
}
