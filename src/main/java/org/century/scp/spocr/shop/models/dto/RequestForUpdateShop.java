package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
public class RequestForUpdateShop extends ShopView {

  private List<String> updatedFields;

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
  public CounterpartyView getCounterparty() {
    return super.getCounterparty();
  }
}
