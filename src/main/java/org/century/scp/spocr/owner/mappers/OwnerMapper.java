package org.century.scp.spocr.owner.mappers;

import org.century.scp.spocr.contact.mappers.ContactMapper;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ContactMapper.class)
public interface OwnerMapper {

  OwnerView map(Owner entity);

  Owner map(OwnerView view);

  default Page<OwnerView> map(Page<Owner> page) {
    return page.map(this::map);
  }
}
