package org.century.scp.spocr.paymentdetails.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;


@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "counterparty_payment_details")
@NoArgsConstructor
public class PaymentDetails extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "payment_account")
  private String paymentAccount;

  @Column(name = "corresponding_account")
  private String correspondingAccount;

  @Column(name = "bic")
  private String bic;

  @Column(name = "bank")
  private String bank;

  @Column(name = "active")
  private Boolean active;

  @Builder
  public PaymentDetails(Long id, Long version, String paymentAccount, String correspondingAccount,
      String bic, String bank, Boolean active) {
    super(version);
    this.id = id;
    this.bic = bic;
    this.bank = bank;
    this.active = active;
    this.paymentAccount = paymentAccount;
    this.correspondingAccount = correspondingAccount;
  }
}
