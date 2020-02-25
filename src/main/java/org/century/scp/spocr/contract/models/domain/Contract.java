package org.century.scp.spocr.contract.models.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.contract.subcontract.models.domain.SubContract;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contracts")
@NoArgsConstructor
public class Contract extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
