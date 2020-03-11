package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class ContractView extends BaseEntityView {

  private String name;
  private Date endDate;
  private Date startDate;
  private String comment;
  private Boolean active;
  private String contractNumber;
  private CounterpartyView counterparty1;
  private CounterpartyView counterparty2;
  private List<SubContractView> subContracts;
}
