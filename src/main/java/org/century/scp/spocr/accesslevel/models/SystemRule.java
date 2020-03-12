package org.century.scp.spocr.accesslevel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.DomainEntity;

@Data
@Entity
@Table(name = "system_rules")
@NoArgsConstructor
@AllArgsConstructor
public class SystemRule implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  public SystemRule(String name) {
    this.name = name;
    this.active = true;
  }
}
