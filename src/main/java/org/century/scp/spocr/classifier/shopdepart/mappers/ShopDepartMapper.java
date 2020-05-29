package org.century.scp.spocr.classifier.shopdepart.mappers;

import java.util.Set;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface ShopDepartMapper {

  Set<ClassifierView> map(Set<ShopDepart> entities);

  ClassifierView map(ShopDepart entity);

  ShopDepart map(ClassifierView view);

  default Page<ClassifierView> map(Page<ShopDepart> page) {
    return page.map(this::map);
  }
}
