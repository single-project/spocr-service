package org.century.scp.spocr.classifier.saleschannel.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.saleschannel.repositories.SalesChannelRepository;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalesChannelServiceImpl extends BaseService<SalesChannel> {

  private final ManufacturerServiceImpl manufacturerService;

  public SalesChannelServiceImpl(
      ManufacturerServiceImpl manufacturerService, DefaultMessageSource messageSource,
      SalesChannelRepository entityRepository) {
    super(messageSource, entityRepository);
    this.manufacturerService = manufacturerService;
  }

  @Override
  public SalesChannel assemble(SalesChannel entity) {
    if (entity.getManufacturer() != null) {
      entity.setManufacturer(manufacturerService.get(entity.getManufacturer().getId()));
    }
    return entity;
  }

  @Override
  public Class<SalesChannel> getEntityClass() {
    return SalesChannel.class;
  }
}
