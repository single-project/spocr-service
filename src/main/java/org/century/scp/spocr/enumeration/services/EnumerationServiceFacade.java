package org.century.scp.spocr.enumeration.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnumerationServiceFacade extends BaseServiceFacade<Enumeration, EnumerationView> {

  public EnumerationServiceFacade(
      ServiceI<Enumeration> service,
      MapperI<Enumeration, EnumerationView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
