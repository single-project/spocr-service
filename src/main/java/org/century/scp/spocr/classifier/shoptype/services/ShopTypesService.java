package org.century.scp.spocr.classifier.shoptype.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopTypesService extends BaseService<ShopType> {

  public ShopTypesService(EntityManager entityManager, BaseRepository<ShopType> repository) {
    super(ShopType.class, entityManager, repository);
  }

  @Override
  public void refresh(ShopType entity) {
    if (entity.getManufacturer() != null && entity.getManufacturer().getId() != null) {
      entity.setManufacturer(getReference(entity.getManufacturer(), Manufacturer.class));
    }
  }
}
