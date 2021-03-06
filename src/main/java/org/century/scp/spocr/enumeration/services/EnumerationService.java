package org.century.scp.spocr.enumeration.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.repositories.EnumerationRepository;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnumerationService extends BaseService<Enumeration> {

  public EnumerationService(EntityManager entityManager, BaseRepository<Enumeration> repository) {
    super(Enumeration.class, entityManager, repository);
  }

  @Override
  public void refresh(Enumeration entity) {
    return;
  }

  @NonNull
  @Override
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Enumeration create(Enumeration entity) {
    entity.buildDescriptionKey();
    return super.create(entity);
  }

  @Cacheable("enumerations")
  public Enumeration getByIdentAndValue(String ident, String value) {
    return ((EnumerationRepository) repository)
        .findByIdentAndValue(ident, value)
        .orElseThrow(() -> new SpocrEntityNotFoundException(Enumeration.class, ident, value));
  }
}
