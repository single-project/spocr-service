package org.century.scp.spocr.classifier.saleschannel.mappers;

import java.util.List;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface SalesChannelMapper {

  List<ClassifierView> map(List<SalesChannel> entities);

  ClassifierView map(SalesChannel entity);

  SalesChannel map(ClassifierView view);

  default Page<ClassifierView> map(Page<SalesChannel> page) {
    return page.map(this::map);
  }
}
