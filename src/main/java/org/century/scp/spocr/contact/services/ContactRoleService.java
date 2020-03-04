package org.century.scp.spocr.contact.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactRoleService extends BaseService<ContactRole> {

  public ContactRoleService(EntityManager entityManager,
      BaseRepository<ContactRole> repository) {
    super(ContactRole.class, entityManager, repository);
  }

  @Override
  public void refresh(ContactRole entity) {
    return;
  }
}
