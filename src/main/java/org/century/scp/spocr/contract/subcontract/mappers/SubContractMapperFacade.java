package org.century.scp.spocr.contract.subcontract.mappers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.contract.subcontract.models.domain.SubContract;
import org.century.scp.spocr.contract.subcontract.models.dto.SubContractView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubContractMapperFacade implements MapperI<SubContract, SubContractView> {

  private final SubContractMapper subContractMapper;

  @Override
  public SubContract map(SubContractView dto) {
    return subContractMapper.map(dto);
  }

  @Override
  public SubContractView map(SubContract domain) {
    return subContractMapper.map(domain);
  }

  @Override
  public Page<SubContractView> map(Page<SubContract> page) {
    return subContractMapper.map(page);
  }
}
