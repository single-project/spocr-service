package org.century.scp.spocr.counterparty.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CounterpartyMapperFacade implements MapperI<Counterparty, CounterpartyView> {

  private final CounterpartyMapper mapper;

  @Override
  public Counterparty map(CounterpartyView dto) {
    return mapper.map(dto);
  }

  @Override
  public CounterpartyView map(Counterparty domain) {
    return mapper.map(domain);
  }

  @Override
  public Page<CounterpartyView> map(Page<Counterparty> page) {
    return mapper.map(page);
  }
}
