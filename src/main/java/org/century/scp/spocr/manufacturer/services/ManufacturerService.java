package org.century.scp.spocr.manufacturer.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManufacturerService extends BaseService<Manufacturer> {

  public ManufacturerService(EntityManager entityManager, BaseRepository<Manufacturer> repository) {
    super(Manufacturer.class, entityManager, repository);
  }

  @Override
  public void refresh(Manufacturer entity) {
    return;
  }
}
