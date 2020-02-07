package org.century.scp.spocr.legaltype.mappers;

import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.legaltype.services.LegalTypeServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper
public abstract class LegalTypeMapper {

  @Autowired
  private LegalTypeServiceImpl service;

  public LegalType resolve(LegalTypeView legalType, @TargetType Class<LegalType> entityClass) {
    return legalType != null && legalType.getId() != null ? service.get(legalType.getId()) : null;
  }

  @Mapping(target = "opfShort", source = "okfpShortName")
  @Mapping(target = "opfFull", source = "okfpFullName")
  @Mapping(target = "opfCode", source = "okpfId")
  @Mapping(target = "opfType", source = "okpfType")
  public abstract LegalTypeView map(LegalType entity);

  public Page<LegalTypeView> map(Page<LegalType> page) {
    return page.map(this::map);
  }
}
