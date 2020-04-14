package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;

@Getter
@Setter
@NoArgsConstructor
public class SubContractView extends BaseEntityView {

  private Long id;
  private String name;
  private String link;
  private String comment;
  private Boolean active;
  private Date subContractDate;
  private ContractView contract;
  private String subContractNumber;
  private EnumerationView status;
}
