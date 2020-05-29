package org.century.scp.spocr.classifier.shoptype.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopTypeMapperFacade implements MapperI<ShopType, ClassifierView> {

  private final ShopTypeMapper shopMapper;

  @Override
  public ShopType map(ClassifierView dto) {
    return shopMapper.map(dto);
  }

  @Override
  public ClassifierView map(ShopType domain) {
    return shopMapper.map(domain);
  }

  @Override
  public Page<ClassifierView> map(Page<ShopType> page) {
    return shopMapper.map(page);
  }
}
