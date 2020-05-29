package org.century.scp.spocr.owner.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OwnerServiceFacade extends BaseServiceFacade<Owner, OwnerView> {

  public OwnerServiceFacade(
      ServiceI<Owner> service,
      MapperI<Owner, OwnerView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
