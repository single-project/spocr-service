package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.LegalRekv;
import org.century.scp.spocr.counterparty.models.dto.LegalRekvView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface LegalRekvMapper {

  LegalRekv map(LegalRekvView view);

  LegalRekvView map(LegalRekv entity);

  default Page<LegalRekvView> map(Page<LegalRekv> legalRekvs) {
    return legalRekvs.map(this::map);
  }
}
