package org.century.scp.spocr.contract.services;

import java.util.List;
import org.century.scp.spocr.contract.subcontract.models.domain.SubContract;
import org.century.scp.spocr.contract.subcontract.models.dto.SubContractView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

public interface SubContractServiceFacadeI {

  @Transactional(readOnly = true)
  SubContractView getSubContract(Long id);

  @Transactional(readOnly = true)
  Page<SubContractView> getSubContracts(Specification<SubContract> specification,
      Pageable pageable);

  @Transactional
  SubContractView createSubContract(SubContractView request);

  @Transactional
  SubContractView updateSubContract(Long id, SubContractView request, List<String> properties);
}
