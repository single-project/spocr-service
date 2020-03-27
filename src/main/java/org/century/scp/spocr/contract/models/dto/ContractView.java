package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

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
  private BaseEntityListView counterparty1;
  private BaseEntityListView counterparty2;
  private List<SubContractView> subContracts;
}
