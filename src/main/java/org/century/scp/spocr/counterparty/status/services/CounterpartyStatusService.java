package org.century.scp.spocr.counterparty.status.services;

import javax.persistence.EntityManager;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.status.models.domain.CounterpartyStatus;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyStatusService extends BaseService<CounterpartyStatus> {

  public CounterpartyStatusService(
      EntityManager entityManager,
      BaseRepository<CounterpartyStatus> repository) {
    super(CounterpartyStatus.class, entityManager, repository);
  }

  @Override
  public void refresh(CounterpartyStatus entity) {
    return;
  }
}
