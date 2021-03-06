package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateContract extends ContractView {

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
  public Date getEndDate() {
    return super.getEndDate();
  }

  @NotNull
  @Override
  public Date getStartDate() {
    return super.getStartDate();
  }

  @NotBlank
  @Override
  public String getContractNumber() {
    return super.getContractNumber();
  }

  @NotNull
  @Override
  public BaseEntityListView getCounterparty1() {
    return super.getCounterparty1();
  }

  @NotNull
  @Override
  public BaseEntityListView getCounterparty2() {
    return super.getCounterparty2();
  }

  @NotNull
  @Override
  public EnumerationView getType() {
    return super.getType();
  }

  @NotNull
  @Override
  public EnumerationView getStatus() {
    return super.getStatus();
  }
}
