package org.century.scp.spocr.contract.subcontract.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.subcontract.models.domain.SubContract;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubContractService extends BaseService<SubContract> {

  public SubContractService(EntityManager entityManager, BaseRepository<SubContract> repository) {
    super(SubContract.class, entityManager, repository);
  }

  @Override
  public void refresh(SubContract entity) {
    if (entity.getContract() != null) {
      Contract contract = getReference(entity.getContract(), Contract.class);
      entity.setContract(contract);
    }
  }
}
