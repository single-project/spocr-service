package org.century.scp.spocr.contract.models.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
@Table(name = "contracts")
@NoArgsConstructor
public class Contract extends AbstractIdentifiedEntity {

  @Cascade({CascadeType.PERSIST})
  @OneToMany(mappedBy = "contract", orphanRemoval = true)
  private List<SubContract> subContracts;

  @Column(name = "contract_number")
  private String contractNumber;

  @Column(name = "name")
  private String name;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "comment")
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_1_id", nullable = false)
  private Counterparty counterparty1;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "counterparty_2_id", nullable = false)
  private Counterparty counterparty2;

  @Column(name = "active")
  private Boolean active;

  public boolean linkedWithSubContracts() {
    return (subContracts != null && subContracts.size() > 0);
  }
}
