package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateShop extends ShopView {

  private List<String> updatedFields;

  @NotNull
  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotNull
  @Positive
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
  public CounterpartyView getCounterparty() {
    return super.getCounterparty();
  }

  @NotNull
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
