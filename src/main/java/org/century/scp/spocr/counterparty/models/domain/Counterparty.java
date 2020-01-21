package org.century.scp.spocr.counterparty.models.domain;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "counterparties")
@NoArgsConstructor
public class Counterparty extends BaseEntity<Counterparty, CounterpartyView> {

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

  @Override
  public CounterpartyView map() {
    return new CounterpartyView(this);
  }

  @Transient
  public Object suggestion;
}
