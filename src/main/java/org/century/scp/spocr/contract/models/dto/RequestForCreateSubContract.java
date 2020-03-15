package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @NotBlank
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

  @NotBlank
  @Override
  public String getSubContractNumber() {
    return super.getSubContractNumber();
  }
}
