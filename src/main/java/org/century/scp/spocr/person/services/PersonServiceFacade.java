package org.century.scp.spocr.person.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.person.models.domain.Person;
import org.century.scp.spocr.person.models.dto.PersonView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonServiceFacade extends BaseServiceFacade<Person, PersonView> {

  public PersonServiceFacade(
      ServiceI<Person> service,
      MapperI<Person, PersonView> mapper, EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
