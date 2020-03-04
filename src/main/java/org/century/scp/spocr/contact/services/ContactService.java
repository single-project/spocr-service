package org.century.scp.spocr.contact.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService extends BaseService<Contact> {

  public ContactService(
      EntityManager entityManager,
      BaseRepository<Contact> repository) {
    super(Contact.class, entityManager, repository);
  }

  @Override
  public void refresh(Contact entity) {
    if (entity.getRole() != null) {
      entity.setRole(getReference(entity.getRole(), ContactRole.class));
    }
  }
}
