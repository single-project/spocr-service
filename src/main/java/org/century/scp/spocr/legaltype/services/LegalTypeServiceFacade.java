package org.century.scp.spocr.legaltype.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LegalTypeServiceFacade extends BaseServiceFacade<LegalType, LegalTypeView> {

  public LegalTypeServiceFacade(
      ServiceI<LegalType> service, MapperI<LegalType, LegalTypeView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
