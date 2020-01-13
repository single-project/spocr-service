package org.century.scp.spocr.manufacturer.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManufacturerServiceImpl extends BaseService<Manufacturer> {

  public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
    super(manufacturerRepository);
  }

  @Override
  public Class<Manufacturer> getEntityClass() {
    return Manufacturer.class;
  }

  @Override
  public String getEntityName() {
    return "производитель";
  }
}