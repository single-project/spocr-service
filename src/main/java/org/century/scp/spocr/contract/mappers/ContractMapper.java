package org.century.scp.spocr.contract.mappers;

import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.dto.ContractView;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.enumeration.mappers.EnumerationMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = {SubContractMapper.class, CounterpartyMapper.class, EnumerationMapper.class})
public abstract class ContractMapper {

  public abstract Contract map(ContractView view);

  public abstract ContractView map(Contract entity);

  public Page<ContractView> map(Page<Contract> page) {
    return page.map(this::map);
  }
}
