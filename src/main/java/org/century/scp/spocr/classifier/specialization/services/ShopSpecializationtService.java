package org.century.scp.spocr.classifier.specialization.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.specialization.domain.ShopSpecialization;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopSpecializationtService extends BaseService<ShopSpecialization> {

  public ShopSpecializationtService(EntityManager entityManager,
      BaseRepository<ShopSpecialization> repository) {
    super(ShopSpecialization.class, entityManager, repository);
  }

  @Override
  public void refresh(ShopSpecialization entity) {
    if (entity.getManufacturer() != null) {
      entity.setManufacturer(getReference(entity.getManufacturer(), Manufacturer.class));
    }
  }
}
