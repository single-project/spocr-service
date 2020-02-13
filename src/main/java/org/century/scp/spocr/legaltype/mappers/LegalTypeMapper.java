package org.century.scp.spocr.legaltype.mappers;

import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper
public abstract class LegalTypeMapper {

  @Mapping(target = "opfShort", source = "okfpShortName")
  @Mapping(target = "opfFull", source = "okfpFullName")
  @Mapping(target = "opfCode", source = "okpfId")
  @Mapping(target = "opfType", source = "okpfType")
  public abstract LegalTypeView map(LegalType entity);

  @InheritInverseConfiguration
  public abstract LegalType map(LegalTypeView view);

  public Page<LegalTypeView> map(Page<LegalType> page) {
    return page.map(this::map);
  }
}
