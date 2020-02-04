package org.century.scp.spocr.paymentdetails.mappers;

import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface PaymentDetailsMapper {

  PaymentDetailsView map(PaymentDetails entity);

  PaymentDetails map(PaymentDetailsView view);

  default Page<PaymentDetailsView> map(Page<PaymentDetails> page) {
    return page.map(this::map);
  }

}
