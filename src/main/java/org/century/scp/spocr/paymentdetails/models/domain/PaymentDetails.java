package org.century.scp.spocr.paymentdetails.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;

@Getter
@Setter
@Entity
@Table(name = "counterparty_payment_details")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails extends AbstractIdentifiedEntity {

  @Column(name = "payment_account")
  private String paymentAccount;

  @Column(name = "corresponding_account")
  private String correspondingAccount;

  @Column(name = "bic")
  private String bic;

  @Column(name = "bank")
  private String bank;

  @ManyToOne(fetch = FetchType.LAZY)
  private Counterparty counterparty;
}
