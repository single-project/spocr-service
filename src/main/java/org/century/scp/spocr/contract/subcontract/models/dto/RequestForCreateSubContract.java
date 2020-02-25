package org.century.scp.spocr.contract.subcontract.models.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.contract.models.dto.ContractView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateSubContract extends SubContractView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

  @Null
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @NotNull
  @NotEmpty
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public Date getSubContractDate() {
    return super.getSubContractDate();
  }

  @NotNull
  @Override
  public ContractView getContract() {
    return super.getContract();
  }

  @NotNull
  @NotEmpty
  @Override
  public String getSubContractNumber() {
    return super.getSubContractNumber();
  }
}
