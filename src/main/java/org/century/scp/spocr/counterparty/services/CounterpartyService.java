package org.century.scp.spocr.counterparty.services;

import java.util.Set;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.status.models.domain.CounterpartyStatus;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyService extends BaseService<Counterparty> {

  public CounterpartyService(EntityManager entityManager, BaseRepository<Counterparty> repository) {
    super(Counterparty.class, entityManager, repository);
  }

  @Override
  public void refresh(Counterparty counterparty) {
    if (counterparty.getParent() != null) {
      counterparty.setParent(getReference(counterparty.getParent(), Counterparty.class));
    }

    if (counterparty.getLegalType() != null) {
      counterparty.setLegalType(getReference(counterparty.getLegalType(), Enumeration.class));
    }

    if (counterparty.linkedWithStatuses()) {
      Set<CounterpartyStatus> statuses = getReferences(counterparty.getStatuses(),
          CounterpartyStatus.class);
      counterparty.setStatuses(statuses);
    }

  }
}
