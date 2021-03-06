package org.century.scp.spocr.contract.services;

import java.util.Set;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContractService extends BaseService<Contract> {

  public ContractService(EntityManager entityManager, BaseRepository<Contract> repository) {
    super(Contract.class, entityManager, repository);
  }

  @Override
  public void refresh(Contract entity) {
    if (entity.getStatus() != null && entity.getStatus().getId() != null) {
      entity.setStatus(getReference(entity.getStatus(), Enumeration.class));
    }

    if (entity.getType() != null && entity.getType().getId() != null) {
      entity.setType(getReference(entity.getType(), Enumeration.class));
    }

    if (entity.linkedWithSubContracts()) {
      Set<SubContract> subContracts = getReferences(entity.getSubContracts(), SubContract.class);
      entity.setSubContracts(subContracts);
    }

    if (entity.getCounterparty1() != null) {
      Counterparty counterparty = getReference(entity.getCounterparty1(), Counterparty.class);
      entity.setCounterparty1(counterparty);
    }

    if (entity.getCounterparty2() != null) {
      Counterparty counterparty = getReference(entity.getCounterparty2(), Counterparty.class);
      entity.setCounterparty2(counterparty);
    }
  }
}
