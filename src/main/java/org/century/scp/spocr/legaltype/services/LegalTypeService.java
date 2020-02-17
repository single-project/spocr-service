package org.century.scp.spocr.legaltype.services;

import javax.persistence.EntityManager;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.springframework.stereotype.Service;

@Service
public class LegalTypeService extends BaseService<LegalType> {

  public LegalTypeService(EntityManager entityManager, BaseRepository<LegalType> repository) {
    super(LegalType.class, entityManager, repository);
  }

  @Override
  public void refresh(LegalType entity) {
    return;
  }
}
