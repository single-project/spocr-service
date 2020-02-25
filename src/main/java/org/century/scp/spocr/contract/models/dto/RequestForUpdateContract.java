package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateContract extends ContractView {

  private List<String> updatedFields;

  @NotNull
  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotNull
  @PositiveOrZero
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
  public Date getEndDate() {
    return super.getEndDate();
  }

  @NotNull
  @Override
  public Date getStartDate() {
    return super.getStartDate();
  }

  @NotNull
  @NotEmpty
  @Override
  public String getContractNumber() {
    return super.getContractNumber();
  }

  @NotNull
  @Override
  public CounterpartyView getCounterparty1() {
    return super.getCounterparty1();
  }

  @NotNull
  @Override
  public CounterpartyView getCounterparty2() {
    return super.getCounterparty2();
  }

  @NotNull
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
