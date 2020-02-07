package org.century.scp.spocr.counterparty.models.dto;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyView extends BaseEntityView {

  private String name;
  private LegalTypeView legalType;
  private LegalRekvView legalRekv;
  private PaymentDetailsView paymentDetails;
  private HashMap suggestion;
  private BaseEntityListView parent;

  public CounterpartyView(String name) {
    this.name = name;
  }

  public CounterpartyView(Long id, String name, Long version, boolean active) {
    super(id, version, active);
    this.name = name;
  }

  public CounterpartyView(
      Long id,
      Boolean active,
      Long version,
      String name,
      LegalTypeView legalType,
      LegalRekvView legalRekv,
      PaymentDetailsView paymentDetails,
      HashMap suggestion) {
    super(id, version, active);
    this.name = name;
    this.legalType = legalType;
    this.legalRekv = legalRekv;
    this.paymentDetails = paymentDetails;
    this.suggestion = suggestion;
  }

  public CounterpartyView(
      Boolean active,
      String name,
      LegalTypeView legalType,
      LegalRekvView legalRekv,
      PaymentDetailsView paymentDetails,
      HashMap suggestion) {
    super(active);
    this.name = name;
    this.legalType = legalType;
    this.legalRekv = legalRekv;
    this.paymentDetails = paymentDetails;
    this.suggestion = suggestion;
  }


}
