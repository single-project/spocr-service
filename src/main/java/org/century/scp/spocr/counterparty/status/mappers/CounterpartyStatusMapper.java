package org.century.scp.spocr.counterparty.status.mappers;

import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.counterparty.status.models.domain.CounterpartyStatus;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface CounterpartyStatusMapper {

  BaseEntityListView map(CounterpartyStatus entity);

  CounterpartyStatus map(BaseEntityListView view);

  default Page<BaseEntityListView> map(Page<CounterpartyStatus> page) {
    return page.map(this::map);
  }
}
