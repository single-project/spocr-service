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
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(
    uses = {
        EnumerationMapper.class,
        PaymentDetailsMapper.class,
        PersonMapper.class,
        OwnerMapper.class,
        ContactMapper.class
    })
public abstract class CounterpartyMapper {

  @Mapping(target = "shortName", source = "legalRekv.shortName")
  @Mapping(target = "fullName", source = "legalRekv.fullName")
  @Mapping(target = "inn", source = "legalRekv.inn")
  @Mapping(target = "kpp", source = "legalRekv.kpp")
  @Mapping(target = "ogrn", source = "legalRekv.ogrn")
  @Mapping(target = "ogrnDate", source = "legalRekv.ogrnDate")
  @Mapping(target = "ogrnAuthority", source = "legalRekv.ogrnAuthority")
  @Mapping(target = "okpo", source = "legalRekv.okpo")
  @Mapping(target = "okonh", source = "legalRekv.okonh")
  public abstract Counterparty map(CounterpartyView req);

  @Mapping(target = "shortName", source = "legalRekv.shortName")
  @Mapping(target = "fullName", source = "legalRekv.fullName")
  @Mapping(target = "inn", source = "legalRekv.inn")
  @Mapping(target = "kpp", source = "legalRekv.kpp")
  @Mapping(target = "ogrn", source = "legalRekv.ogrn")
  @Mapping(target = "ogrnDate", source = "legalRekv.ogrnDate")
  @Mapping(target = "ogrnAuthority", source = "legalRekv.ogrnAuthority")
  @Mapping(target = "okpo", source = "legalRekv.okpo")
  @Mapping(target = "okonh", source = "legalRekv.okonh")
  public abstract Counterparty map(RequestForUpdateCounterparty req);

  @Mapping(target = "legalRekv.shortName", source = "shortName")
  @Mapping(target = "legalRekv.fullName", source = "fullName")
  @Mapping(target = "legalRekv.inn", source = "inn")
  @Mapping(target = "legalRekv.kpp", source = "kpp")
  @Mapping(target = "legalRekv.ogrn", source = "ogrn")
  @Mapping(target = "legalRekv.ogrnDate", source = "ogrnDate")
  @Mapping(target = "legalRekv.ogrnAuthority", source = "ogrnAuthority")
  @Mapping(target = "legalRekv.okpo", source = "okpo")
  @Mapping(target = "legalRekv.okonh", source = "okonh")
  public abstract CounterpartyView map(Counterparty entity);

  public Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
