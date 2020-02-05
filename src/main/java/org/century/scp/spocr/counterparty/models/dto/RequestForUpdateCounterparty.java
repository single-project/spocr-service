package org.century.scp.spocr.counterparty.models.dto;

import java.util.HashMap;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateCounterparty extends CounterpartyView {

  private List<String> updatedFields;

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
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return this.updatedFields;
  }

  @Builder
  public RequestForUpdateCounterparty(
      Long id,
      String name,
      Long version,
      boolean active,
      List<String> updatedFields,
      LegalTypeView legalType,
      LegalRekvView legalRekv,
      PaymentDetailsView paymentDetails,
      HashMap suggestion) {
    super(id, active, version, name, legalType, legalRekv, paymentDetails, suggestion);
    this.updatedFields = updatedFields;
  }
}
