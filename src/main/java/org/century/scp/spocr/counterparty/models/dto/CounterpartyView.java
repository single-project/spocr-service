package org.century.scp.spocr.counterparty.models.dto;

import java.util.HashMap;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyView extends BaseEntityView {

  private String name;
  private EnumerationView legalType;
  private LegalRekvView legalRekv;
  private PaymentDetailsView paymentDetails;
  private HashMap suggestion;
  private BaseEntityListView parent;
  private Set<BaseEntityListView> statuses;

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
      EnumerationView legalType,
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
      EnumerationView legalType,
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
