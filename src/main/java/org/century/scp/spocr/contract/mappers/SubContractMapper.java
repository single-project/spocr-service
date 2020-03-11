package org.century.scp.spocr.contract.mappers;

import java.util.List;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.models.dto.SubContractView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public abstract class SubContractMapper {

  public abstract SubContract map(SubContractView view);

  public abstract SubContractView map(SubContract entity);

  public abstract List<SubContractView> map(List<SubContract> subContracts);

  public Page<SubContractView> map(Page<SubContract> page) {
    return page.map(this::map);
  }

}
