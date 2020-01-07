package org.century.scp.spocr.counterparties.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "counterparties")
@NoArgsConstructor
public class Counterparty extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  public Counterparty(String name) {
    this.name = name;
    this.active = true;
  }
}
