package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateShop extends ShopView {

  public RequestForCreateShop(String name, boolean active,
      AddressView address,
      List<ClassifierView> shopTypes,
      List<ClassifierView> salesChannels,
      CounterpartyView counterparty) {
    super(name, active, address, shopTypes, salesChannels, counterparty);
  }

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

  @NotNull
  @Override
  public AddressView getAddress() {
    return super.getAddress();
  }
}
