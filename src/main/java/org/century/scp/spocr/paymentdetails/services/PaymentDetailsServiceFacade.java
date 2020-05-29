package org.century.scp.spocr.paymentdetails.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentDetailsServiceFacade extends
    BaseServiceFacade<PaymentDetails, PaymentDetailsView> {

  public PaymentDetailsServiceFacade(
      ServiceI<PaymentDetails> service,
      MapperI<PaymentDetails, PaymentDetailsView> mapper, EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
