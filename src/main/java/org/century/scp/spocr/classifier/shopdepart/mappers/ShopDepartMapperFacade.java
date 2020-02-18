package org.century.scp.spocr.classifier.shopdepart.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopDepartMapperFacade implements MapperI<ShopDepart, ClassifierView> {

  private final ShopDepartMapper shopMapper;

  @Override
  public ShopDepart map(ClassifierView dto) {
    return shopMapper.map(dto);
  }

  @Override
  public ClassifierView map(ShopDepart domain) {
    return shopMapper.map(domain);
  }

  @Override
  public Page<ClassifierView> map(Page<ShopDepart> page) {
    return shopMapper.map(page);
  }
}
