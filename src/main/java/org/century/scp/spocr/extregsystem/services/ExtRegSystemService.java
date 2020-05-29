package org.century.scp.spocr.extregsystem.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExtRegSystemService extends BaseService<ExtRegSystem> {

  public ExtRegSystemService(EntityManager entityManager,
      BaseRepository<ExtRegSystem> repository) {
    super(ExtRegSystem.class, entityManager, repository);
  }

  @Override
  public void refresh(ExtRegSystem entity) {
    return;
  }
}
