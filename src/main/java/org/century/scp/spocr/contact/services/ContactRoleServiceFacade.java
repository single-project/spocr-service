package org.century.scp.spocr.contact.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.century.scp.spocr.contact.models.dto.ContactRoleView;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContactRoleServiceFacade extends BaseServiceFacade<ContactRole, ContactRoleView> {

  public ContactRoleServiceFacade(
      ServiceI<ContactRole> service,
      MapperI<ContactRole, ContactRoleView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
