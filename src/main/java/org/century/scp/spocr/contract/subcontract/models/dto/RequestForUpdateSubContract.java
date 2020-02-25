package org.century.scp.spocr.contract.subcontract.models.dto;

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
import org.century.scp.spocr.contract.models.dto.ContractView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateSubContract extends SubContractView {

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

  @NotNull
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
