package org.century.scp.spocr.manufacturer.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManufacturerServiceImpl extends BaseService<Manufacturer> {

  public ManufacturerServiceImpl(DefaultMessageSource messageSource,
      ManufacturerRepository manufacturerRepository) {
    super(messageSource, manufacturerRepository);
  }

  @Override
  public Class<Manufacturer> getEntityClass() {
    return Manufacturer.class;
  }

}
