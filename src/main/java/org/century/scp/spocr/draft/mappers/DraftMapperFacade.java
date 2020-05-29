package org.century.scp.spocr.draft.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.draft.models.domain.Draft;
import org.century.scp.spocr.draft.models.dto.DraftView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DraftMapperFacade implements MapperI<Draft, DraftView> {

  private final DraftMapper draftMapper;

  @Override
  public Draft map(DraftView dto) {
    return draftMapper.map(dto);
  }

  @Override
  public DraftView map(Draft domain) {
    return draftMapper.map(domain);
  }

  @Override
  public Page<DraftView> map(Page<Draft> page) {
    return draftMapper.map(page);
  }
}
