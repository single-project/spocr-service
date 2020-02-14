package org.century.scp.spocr.classifier.shoptype.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.shoptype.repositories.ShopTypeRepository;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopTypesServiceImpl extends BaseService<ShopType> {

  private ManufacturerServiceImpl manufacturerService;

  public ShopTypesServiceImpl(
      ManufacturerServiceImpl manufacturerService, DefaultMessageSource messageSource,
      ShopTypeRepository shopTypeRepository) {
    super(messageSource, shopTypeRepository);
    this.manufacturerService = manufacturerService;
  }

  @Override
  public ShopType assemble(ShopType entity) {
    if (entity.getManufacturer() != null) {
      entity.setManufacturer(manufacturerService.get(entity.getManufacturer().getId()));
    }
    return entity;
  }

  @Override
  public Class<ShopType> getEntityClass() {
    return ShopType.class;
  }
}
