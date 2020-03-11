package org.century.scp.spocr.contract.services;

import java.util.List;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.models.dto.SubContractView;
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
  Long createSubContract(SubContractView request);

  @Transactional
  void updateSubContract(Long id, SubContractView request, List<String> properties);
}
