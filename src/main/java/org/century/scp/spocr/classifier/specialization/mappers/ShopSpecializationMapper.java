package org.century.scp.spocr.classifier.specialization.mappers;

import java.util.Set;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.specialization.domain.ShopSpecialization;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface ShopSpecializationMapper {

  Set<ClassifierView> map(Set<ShopSpecialization> entities);

  ClassifierView map(ShopSpecialization entity);

  ShopSpecialization map(ClassifierView view);

  default Page<ClassifierView> map(Page<ShopSpecialization> page) {
    return page.map(this::map);
  }
}
