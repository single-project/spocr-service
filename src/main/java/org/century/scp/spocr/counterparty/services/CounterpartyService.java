package org.century.scp.spocr.counterparty.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyService extends BaseService<Counterparty> {

  public CounterpartyService(EntityManager entityManager, BaseRepository<Counterparty> repository) {
    super(Counterparty.class, entityManager, repository);
  }

  @Override
  public void refresh(Counterparty entity) {
    if (entity.getParent() != null) {
      entity.setParent(getReference(entity.getParent(), Counterparty.class));
    }

    if (entity.getLegalType() != null) {
      entity.setLegalType(getReference(entity.getLegalType(), LegalType.class));
    }
  }
}
