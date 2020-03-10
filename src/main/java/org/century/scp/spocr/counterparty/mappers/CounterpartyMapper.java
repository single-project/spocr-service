package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.contact.mappers.ContactMapper;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.counterparty.models.dto.RequestForUpdateCounterparty;
import org.century.scp.spocr.enumeration.mappers.EnumerationMapper;
import org.century.scp.spocr.owner.mappers.OwnerMapper;
import org.century.scp.spocr.paymentdetails.mappers.PaymentDetailsMapper;
import org.century.scp.spocr.person.mappers.PersonMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(
    uses = {
        EnumerationMapper.class,
        PaymentDetailsMapper.class,
        PersonMapper.class,
        OwnerMapper.class,
        ContactMapper.class,
        LegalRekvMapper.class
    })
public interface CounterpartyMapper {

  Counterparty map(CounterpartyView req);

  Counterparty map(RequestForUpdateCounterparty req);

  CounterpartyView map(Counterparty entity);

  default Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
