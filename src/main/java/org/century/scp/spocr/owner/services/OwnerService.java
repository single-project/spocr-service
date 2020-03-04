package org.century.scp.spocr.owner.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OwnerService extends BaseService<Owner> {

  public OwnerService(EntityManager entityManager, BaseRepository<Owner> repository) {
    super(Owner.class, entityManager, repository);
  }

  @Override
  public void refresh(Owner entity) {
    if (entity.linkedWithContacts()) {
      entity.setContacts(getReferences(entity.getContacts(), Contact.class));
    }
  }
}
