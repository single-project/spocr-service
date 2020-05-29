package org.century.scp.spocr.contract.models.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;

@Entity
@Getter
@Setter
@Table(name = "sub_contracts")
@NoArgsConstructor
public class SubContract extends AbstractIdentifiedEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "contract_id", nullable = false)
  private Contract contract;

  @Column(name = "sub_contract_number")
  private String subContractNumber;

  @Column(name = "name")
  private String name;

  @Column(name = "sub_contract_date")
  private Date subContractDate;

  @Column(name = "comment")
  private String comment;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "link")
  private String link;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id")
  private Enumeration status;

}
