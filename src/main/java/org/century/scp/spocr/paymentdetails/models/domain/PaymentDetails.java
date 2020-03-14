package org.century.scp.spocr.paymentdetails.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;

@Data
@Entity
@Table(name = "counterparty_payment_details")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails implements DomainEntity {

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

  @ManyToOne(fetch = FetchType.LAZY)
  private Counterparty counterparty;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaymentDetails)) {
      return false;
    }
    return id != null && id.equals(((PaymentDetails) o).getId());
  }

  @Override
  public int hashCode() {
    return 31;
  }



}
