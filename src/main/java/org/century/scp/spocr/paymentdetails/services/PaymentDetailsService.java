package org.century.scp.spocr.paymentdetails.services;

import javax.persistence.EntityManager;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsService extends BaseService<PaymentDetails> {

  public PaymentDetailsService(
      EntityManager entityManager,
      BaseRepository<PaymentDetails> repository) {
    super(PaymentDetails.class, entityManager, repository);
  }

  @Override
  public void refresh(PaymentDetails entity) {
    return;
  }
}
