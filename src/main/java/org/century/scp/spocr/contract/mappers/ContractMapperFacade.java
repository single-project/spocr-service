package org.century.scp.spocr.contract.mappers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.dto.ContractView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContractMapperFacade implements MapperI<Contract, ContractView> {

  private final ContractMapper contractMapper;

  @Override
  public Contract map(ContractView dto) {
    return contractMapper.map(dto);
  }

  @Override
  public ContractView map(Contract domain) {
    return contractMapper.map(domain);
  }

  @Override
  public Page<ContractView> map(Page<Contract> page) {
    return contractMapper.map(page);
  }
}
