package org.century.scp.spocr.contract.models.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateContract extends ContractView {

  private List<String> updatedFields;

  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

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

  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
