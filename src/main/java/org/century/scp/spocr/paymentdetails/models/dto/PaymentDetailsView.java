package org.century.scp.spocr.paymentdetails.models.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsView extends BaseEntityView {

  @Column(name = "payment_account")
  private String paymentAccount;

  @Column(name = "corresponding_account")
  private String correspondingAccount;

  @Column(name = "bic")
  private String bic;

  @Column(name = "bank")
  private String bank;
}
