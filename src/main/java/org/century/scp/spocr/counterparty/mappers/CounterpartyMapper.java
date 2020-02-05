package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.legaltype.mappers.LegalTypeMapper;
import org.century.scp.spocr.paymentdetails.mappers.PaymentDetailsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    uses = {PaymentDetailsMapper.class, LegalTypeMapper.class})
public interface CounterpartyMapper {

  @Mapping(target = "legalRekv.shortName", source = "shortName")
  @Mapping(target = "legalRekv.fullName", source = "fullName")
  @Mapping(target = "legalRekv.inn", source = "inn")
  @Mapping(target = "legalRekv.kpp", source = "kpp")
  @Mapping(target = "legalRekv.ogrn", source = "ogrn")
  @Mapping(target = "legalRekv.ogrnDate", source = "ogrnDate")
  @Mapping(target = "legalRekv.ogrnAuthority", source = "ogrnAuthority")
  @Mapping(target = "legalRekv.okpo", source = "okpo")
  @Mapping(target = "legalRekv.okonh", source = "okonh")
  CounterpartyView map(Counterparty entity);

  @Mapping(target = "name", source = "legalRekv.shortName")
  @Mapping(target = "fullName", source = "legalRekv.fullName")
  @Mapping(target = "inn", source = "legalRekv.inn")
  @Mapping(target = "kpp", source = "legalRekv.kpp")
  @Mapping(target = "ogrn", source = "legalRekv.ogrn")
  @Mapping(target = "ogrnDate", source = "legalRekv.ogrnDate")
  @Mapping(target = "ogrnAuthority", source = "legalRekv.ogrnAuthority")
  @Mapping(target = "okpo", source = "legalRekv.okpo")
  @Mapping(target = "okonh", source = "legalRekv.okonh")
  Counterparty map(CounterpartyView view);

  default Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
