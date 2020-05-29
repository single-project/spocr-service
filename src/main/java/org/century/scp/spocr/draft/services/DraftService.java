package org.century.scp.spocr.draft.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.draft.models.domain.Draft;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DraftService extends BaseService<Draft> {

  public DraftService(EntityManager entityManager,
      BaseRepository<Draft> repository) {
    super(Draft.class, entityManager, repository);
  }

  @Override
  public void refresh(Draft entity) {
    return;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
