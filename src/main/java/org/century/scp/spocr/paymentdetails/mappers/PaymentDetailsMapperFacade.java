package org.century.scp.spocr.paymentdetails.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentDetailsMapperFacade implements MapperI<PaymentDetails, PaymentDetailsView> {

  private final PaymentDetailsMapper mapper;

  @Override
  public PaymentDetails map(PaymentDetailsView dto) {
    return mapper.map(dto);
  }

  @Override
  public PaymentDetailsView map(PaymentDetails domain) {
    return mapper.map(domain);
  }

  @Override
  public Page<PaymentDetailsView> map(Page<PaymentDetails> page) {
    return mapper.map(page);
  }
}
