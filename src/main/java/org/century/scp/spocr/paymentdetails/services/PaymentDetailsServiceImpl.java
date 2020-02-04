package org.century.scp.spocr.paymentdetails.services;

import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.respositories.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsServiceImpl extends BaseService<PaymentDetails> {

  @Autowired
  public PaymentDetailsServiceImpl(
      PaymentDetailsRepository entityRepository) {
    super(entityRepository);
  }

  @Override
  public Class<PaymentDetails> getEntityClass() {
    return PaymentDetails.class;
  }

  @Override
  public String getEntityName() {
    return "банковские реквизиты";
  }
}
