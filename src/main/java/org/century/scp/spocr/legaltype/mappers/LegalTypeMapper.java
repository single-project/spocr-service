package org.century.scp.spocr.legaltype.mappers;

import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper
public interface LegalTypeMapper {

  @Mapping(target = "opfShort", source = "okfpShortName")
  @Mapping(target = "opfFull", source = "okfpFullName")
  @Mapping(target = "opfCode", source = "okpfId")
  @Mapping(target = "opfType", source = "okpfType")
  LegalTypeView map(LegalType entity);

  @Mapping(target = "okfpShortName", source = "opfShort")
  @Mapping(target = "okfpFullName", source = "opfFull")
  @Mapping(target = "okpfId", source = "opfCode")
  @Mapping(target = "okpfType", source = "opfType")
  LegalType map(LegalTypeView view);

  default Page<LegalTypeView> map(Page<LegalType> page) {
    return page.map(this::map);
  }
}
