package org.century.scp.spocr.enumeration.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnumerationMapperFacade implements MapperI<Enumeration, EnumerationView> {

  private final EnumerationMapper enumerationMapper;

  @Override
  public Enumeration map(EnumerationView dto) {
    return enumerationMapper.map(dto);
  }

  @Override
  public EnumerationView map(Enumeration domain) {
    return enumerationMapper.map(domain);
  }

  @Override
  public Page<EnumerationView> map(Page<Enumeration> page) {
    return enumerationMapper.map(page);
  }
}
