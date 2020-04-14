package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;

@Getter
@Setter
@NoArgsConstructor
public class ContractView extends BaseEntityView {
  private String name;
  private String link;
  private Date endDate;
  private Date startDate;
  private String comment;
  private Boolean active;
  private String contractNumber;
  private EnumerationView type;
  private EnumerationView status;
  private String commodityCredit;
  private Boolean autoprolongation;
  private BaseEntityListView counterparty1;
  private BaseEntityListView counterparty2;
  private List<SubContractView> subContracts;
}
