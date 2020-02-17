package org.century.scp.spocr.base.servicefacades;

import java.util.List;
import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.century.scp.spocr.base.models.dto.DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceFacade<T extends DomainEntity, K extends DTO> {

  @Transactional(readOnly = true)
  K get(Long id);

  @Transactional(readOnly = true)
  Page<K> get(Specification<T> specification, Pageable pageable);

  @Transactional
  K create(K request);

  @Transactional
  K update(Long id, K request, List<String> properties);


}
