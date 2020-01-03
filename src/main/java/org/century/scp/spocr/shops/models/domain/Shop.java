package org.century.scp.spocr.shops.models.domain;

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
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.events.models.domain.AbstractAuditableEntity;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends AbstractAuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_id", nullable = false)
  private Counterparty counterparty;

  @Column(name = "active")
  private Boolean active;

}
