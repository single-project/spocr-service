package org.century.scp.spocr.counterparty.models.dto;

import java.util.HashMap;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateCounterparty extends CounterpartyView {

  @Builder
  public RequestForCreateCounterparty(
      String name,
      LegalTypeView legalType,
      LegalRekvView legalRekv,
      PaymentDetailsView paymentDetails,
      HashMap suggestion,
      BaseEntityListView parent) {
    super(name, legalType, legalRekv, paymentDetails, suggestion, parent);
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

  @Override
  public BaseEntityListView getParent() {
    return super.getParent();
  }
}
