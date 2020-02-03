package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.counterparty.models.dto.RequestForCreateUpdateCounterparty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CounterpartyMapper {

  @Mapping(target = "updatedFields", ignore = true)
  CounterpartyView map(Counterparty entity);

  Counterparty map(CounterpartyView view);

  Counterparty map(RequestForCreateUpdateCounterparty view);

  default Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
