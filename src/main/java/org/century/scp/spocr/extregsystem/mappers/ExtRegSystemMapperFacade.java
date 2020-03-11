package org.century.scp.spocr.extregsystem.mappers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtRegSystemMapperFacade implements MapperI<ExtRegSystem, ExtRegSystemView> {

  private final ExtRegSystemMapper extRegSystemMapper;

  @Override
  public ExtRegSystem map(ExtRegSystemView dto) {
    return extRegSystemMapper.map(dto);
  }

  @Override
  public ExtRegSystemView map(ExtRegSystem domain) {
    return extRegSystemMapper.map(domain);
  }

  @Override
  public Page<ExtRegSystemView> map(Page<ExtRegSystem> page) {
    return extRegSystemMapper.map(page);
  }
}
