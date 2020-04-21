package org.century.scp.spocr.contract.models.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@Table(name = "contracts")
@DynamicInsert
@NoArgsConstructor
public class Contract extends AbstractIdentifiedEntity {

  @OneToMany(
      mappedBy = "contract",
      fetch = FetchType.EAGER,
      cascade = javax.persistence.CascadeType.ALL,
      orphanRemoval = true)
  private Set<SubContract> subContracts;

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

  @Column(name = "commodity_credit")
  private String commodityCredit;

  @Column(name = "autoprolongation")
  @ColumnDefault("false")
  private Boolean autoprolongation;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_id")
  private Enumeration type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id")
  private Enumeration status;

  @Column(name = "link")
  private String link;

  public boolean linkedWithSubContracts() {
    return (subContracts != null && subContracts.size() > 0);
  }

  public void setSubContracts(Set<SubContract> subContracts) {
    if (linkedWithSubContracts()) {
      this.subContracts.removeIf(
          e -> {
            e.setContract(null);
            return true;
          });
      if (subContracts != null) {
        subContracts.forEach(this::addSubContract);
      }
    } else if (subContracts != null) {
      subContracts.forEach(this::addSubContract);
    }
  }

  public void addSubContract(SubContract subContract) {
    if (this.subContracts == null) {
      this.subContracts = new HashSet<>();
    }
    getSubContracts().add(subContract);
    subContract.setContract(this);
  }
}
