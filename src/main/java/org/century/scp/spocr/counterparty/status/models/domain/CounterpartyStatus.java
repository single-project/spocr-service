package org.century.scp.spocr.counterparty.status.models.domain;

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
import org.century.scp.spocr.base.models.domain.DomainEntity;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "counterparty_statuses")
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyStatus implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  public CounterpartyStatus(String name) {
    this.name = name;
  }
}
