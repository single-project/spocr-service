package org.century.scp.spocr.draft.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.draft.models.domain.Draft;
import org.century.scp.spocr.draft.models.dto.DraftView;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DraftServiceFacade extends BaseServiceFacade<Draft, DraftView> {

  public DraftServiceFacade(
      ServiceI<Draft> service,
      MapperI<Draft, DraftView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
