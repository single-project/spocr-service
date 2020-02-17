package org.century.scp.spocr.legaltype.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LegalTypeMapperFacade implements MapperI<LegalType, LegalTypeView> {

  private final LegalTypeMapper mapper;

  @Override
  public LegalType map(LegalTypeView dto) {
    return mapper.map(dto);
  }

  @Override
  public LegalTypeView map(LegalType domain) {
    return mapper.map(domain);
  }

  @Override
  public Page<LegalTypeView> map(Page<LegalType> page) {
    return mapper.map(page);
  }
}
