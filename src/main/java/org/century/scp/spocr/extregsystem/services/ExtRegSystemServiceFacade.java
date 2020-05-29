package org.century.scp.spocr.extregsystem.services;

import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;
import org.springframework.stereotype.Component;

@Component
public class ExtRegSystemServiceFacade extends BaseServiceFacade<ExtRegSystem, ExtRegSystemView> {

  public ExtRegSystemServiceFacade(
      ServiceI<ExtRegSystem> service,
      MapperI<ExtRegSystem, ExtRegSystemView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
