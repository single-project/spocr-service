package org.century.scp.spocr.base.mappers;

import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.century.scp.spocr.base.models.dto.DTO;
import org.springframework.data.domain.Page;

public interface MapperI<T extends DomainEntity, K extends DTO> {

  T map(K dto);

  K map(T domain);

  Page<K> map(Page<T> page);

}
