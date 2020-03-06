package org.century.scp.spocr.classifier.saleschannel.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalesChannelService extends BaseService<SalesChannel> {

  public SalesChannelService(
      EntityManager entityManager, BaseRepository<SalesChannel> repository) {
    super(SalesChannel.class, entityManager, repository);
  }

  @Override
  public void refresh(SalesChannel entity) {
    if (entity.getManufacturer() != null && entity.getManufacturer().getId() != null) {
      entity.setManufacturer(getReference(entity.getManufacturer(), Manufacturer.class));
    }
  }
}
