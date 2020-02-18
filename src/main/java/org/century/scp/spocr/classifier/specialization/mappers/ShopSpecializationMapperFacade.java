package org.century.scp.spocr.classifier.specialization.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.specialization.domain.ShopSpecialization;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopSpecializationMapperFacade implements MapperI<ShopSpecialization, ClassifierView> {

  private final ShopSpecializationMapper shopMapper;

  @Override
  public ShopSpecialization map(ClassifierView dto) {
    return shopMapper.map(dto);
  }

  @Override
  public ClassifierView map(ShopSpecialization domain) {
    return shopMapper.map(domain);
  }

  @Override
  public Page<ClassifierView> map(Page<ShopSpecialization> page) {
    return shopMapper.map(page);
  }
}
