package org.century.scp.spocr.contract.services;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.dto.ContractView;
import org.century.scp.spocr.contract.subcontract.models.domain.SubContract;
import org.century.scp.spocr.contract.subcontract.models.dto.SubContractView;
import org.century.scp.spocr.contract.subcontract.services.SubContractServiceFacade;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContractServiceFacade extends BaseServiceFacade<Contract, ContractView> implements
    SubContractServiceFacadeI {

  private final SubContractServiceFacade subContractServiceFacade;

  public ContractServiceFacade(
      SubContractServiceFacade subContractServiceFacade,
      ServiceI<Contract> service,
      MapperI<Contract, ContractView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
    this.subContractServiceFacade = subContractServiceFacade;
  }

  @Override
  public SubContractView getSubContract(Long id) {
    return subContractServiceFacade.get(id);
  }

  @Override
  public Page<SubContractView> getSubContracts(Specification<SubContract> specification,
      Pageable pageable) {
    return subContractServiceFacade.get(specification, pageable);
  }

  @Override
  public SubContractView createSubContract(SubContractView request) {
    return subContractServiceFacade.create(request);
  }

  @Override
  public SubContractView updateSubContract(Long id, SubContractView request,
      List<String> properties) {
    return subContractServiceFacade.update(id, request, properties);
  }
}
