package org.century.scp.spocr.counterparty.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

@Getter
@Setter
@NoArgsConstructor
public class CounterpartyView extends BaseEntityView {

  private String name;
  private LegalTypeView legalType;
  private String fullName;
  private String inn;
  private String kpp;
  private String ogrn;
  private String ogrnDate;
  private String ogrnAuthority;
  private String okpo;
  private String okonh;
  private PaymentDetailsView paymentDetails;

  public CounterpartyView(String name) {
    this.name = name;
  }

  public CounterpartyView(Long id, String name, Long version, boolean active) {
    super(id, version, active);
    this.name = name;
  }

}
