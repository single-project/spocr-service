package org.century.scp.spocr.contract.models.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sub_contracts")
@NoArgsConstructor
public class SubContract extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "contract_id", nullable = false)
  private Contract contract;

  @Column(name = "sub_contract_number")
  private String subContractNumber;

  @Column(name = "name")
  private String name;

  @Column(name = "sub_contract_date")
  private Date subContract;

  @Column(name = "comment")
  private String comment;

  @Column(name = "active")
  private Boolean active;

}
