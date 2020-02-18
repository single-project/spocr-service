package org.century.scp.spocr.classifier.shopdepart.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopDepartService extends BaseService<ShopDepart> {

  public ShopDepartService(EntityManager entityManager, BaseRepository<ShopDepart> repository) {
    super(ShopDepart.class, entityManager, repository);
  }

  @Override
  public void refresh(ShopDepart entity) {
    if (entity.getManufacturer() != null) {
      entity.setManufacturer(getReference(entity.getManufacturer(), Manufacturer.class));
    }
  }
}
