package org.century.scp.spocr.paymentdetails.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDetailsView extends BaseEntityView {
  private String paymentAccount;
  private String correspondingAccount;
  private String bic;
  private String bank;

}
