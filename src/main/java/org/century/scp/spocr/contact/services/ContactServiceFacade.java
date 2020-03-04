package org.century.scp.spocr.contact.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContactServiceFacade extends BaseServiceFacade<Contact, ContactView> {

  public ContactServiceFacade(
      ServiceI<Contact> service,
      MapperI<Contact, ContactView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
