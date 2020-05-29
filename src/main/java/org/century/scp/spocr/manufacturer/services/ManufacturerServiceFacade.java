package org.century.scp.spocr.manufacturer.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ManufacturerServiceFacade extends BaseServiceFacade<Manufacturer, ManufacturerView> {

  public ManufacturerServiceFacade(
      ServiceI<Manufacturer> service, MapperI<Manufacturer, ManufacturerView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
