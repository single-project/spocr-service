package org.century.scp.spocr.shoptype.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.repositories.ShopTypeRepository;
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

  public List<ShopType> getAll(List<ShopType> shopTypes) {
    return entityRepository.findAllById(getIds(shopTypes));
  }

  private List<Long> getIds(Collection<ShopType> shopTypes) {
    return shopTypes.stream().map(ShopType::getId).collect(Collectors.toList());
  }
}
