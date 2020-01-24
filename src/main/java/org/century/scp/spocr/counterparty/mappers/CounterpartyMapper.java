package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface CounterpartyMapper {

  CounterpartyView map(Counterparty entity);

  Counterparty map(CounterpartyView view);

  default Page<CounterpartyView> map(Page<Counterparty> page) {
    return page.map(this::map);
  }
}
