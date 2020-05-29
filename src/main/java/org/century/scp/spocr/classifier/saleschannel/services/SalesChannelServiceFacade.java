package org.century.scp.spocr.classifier.saleschannel.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesChannelServiceFacade extends BaseServiceFacade<SalesChannel, ClassifierView> {

  public SalesChannelServiceFacade(
      ServiceI<SalesChannel> service, MapperI<SalesChannel, ClassifierView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
