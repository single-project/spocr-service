package org.century.scp.spocr.contract.mappers;

import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.models.dto.SubContractView;
import org.century.scp.spocr.enumeration.mappers.EnumerationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(uses = EnumerationMapper.class)
public abstract class SubContractMapper {

  public abstract SubContract map(SubContractView view);

  @Mapping(target = "contract", ignore = true)
  public abstract SubContractView map(SubContract entity);

  @Mapping(target = "contract", ignore = true)
  public Page<SubContractView> map(Page<SubContract> page) {
    return page.map(this::map);
  }
}
