package org.century.scp.spocr.shoptypes.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.repositories.ShopTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopTypesServiceImpl extends BaseService<ShopType> {

  @Autowired
  public ShopTypesServiceImpl(ShopTypeRepository shopTypeRepository) {
    super(shopTypeRepository);
  }

  @Override
  public Class<ShopType> getEntityClass() {
    return ShopType.class;
  }

  @Override
  public String getEntityName() {
    return "тип магазина";
  }
}
