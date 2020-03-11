package org.century.scp.spocr.extregsystem.mappers;

import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface ExtRegSystemMapper {

  ExtRegSystem map(ExtRegSystemView view);

  ExtRegSystemView map(ExtRegSystem entity);

  default Page<ExtRegSystemView> map(Page<ExtRegSystem> extRegSystems) {
    return extRegSystems.map(this::map);
  }

}
