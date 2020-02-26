package org.century.scp.spocr.enumeration.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnumerationService extends BaseService<Enumeration> {


  public EnumerationService(
      EntityManager entityManager,
      BaseRepository<Enumeration> repository) {
    super(Enumeration.class, entityManager, repository);
  }

  @Override
  public void refresh(Enumeration entity) {
    return;
  }
}
