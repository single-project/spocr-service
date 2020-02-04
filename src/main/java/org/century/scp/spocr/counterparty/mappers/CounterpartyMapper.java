package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.paymentdetails.mappers.PaymentDetailsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    uses = {PaymentDetailsMapper.class})
public interface CounterpartyMapper {

  CounterpartyView map(Counterparty entity);

  Counterparty map(CounterpartyView view);

  default Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
