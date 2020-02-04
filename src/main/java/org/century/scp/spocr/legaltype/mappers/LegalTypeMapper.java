package org.century.scp.spocr.legaltype.mappers;

import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface LegalTypeMapper {

  LegalTypeView map(LegalType entity);

  LegalType map(LegalTypeView view);

  default Page<LegalTypeView> map(Page<LegalType> page) {
    return page.map(this::map);
  }
}
