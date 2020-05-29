package org.century.scp.spocr.classifier.saleschannel.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesChannelMapperFacade implements MapperI<SalesChannel, ClassifierView> {

  private final SalesChannelMapper shopMapper;

  @Override
  public SalesChannel map(ClassifierView dto) {
    return shopMapper.map(dto);
  }

  @Override
  public ClassifierView map(SalesChannel domain) {
    return shopMapper.map(domain);
  }

  @Override
  public Page<ClassifierView> map(Page<SalesChannel> page) {
    return shopMapper.map(page);
  }
}
