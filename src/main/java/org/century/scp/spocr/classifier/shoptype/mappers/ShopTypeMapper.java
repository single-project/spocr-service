package org.century.scp.spocr.classifier.shoptype.mappers;

import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface ShopTypeMapper {

  ClassifierView map(ShopType entity);

  ShopType map(ClassifierView view);

  default Page<ClassifierView> map(Page<ShopType> page) {
    return page.map(this::map);
  }
}
