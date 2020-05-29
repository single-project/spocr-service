package org.century.scp.spocr.classifier.mappers;

import org.century.scp.spocr.classifier.domain.Classifier;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface ClassifierMapper {

  ClassifierView map(Classifier entity);

  Classifier map(ClassifierView view);

  default Page<ClassifierView> map(Page<Classifier> page) {
    return page.map(this::map);
  }
}
