package org.century.scp.spocr.owner.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerMapperFacade implements MapperI<Owner, OwnerView> {

  private final OwnerMapper ownerMapper;

  @Override
  public Owner map(OwnerView dto) {
    return ownerMapper.map(dto);
  }

  @Override
  public OwnerView map(Owner domain) {
    return ownerMapper.map(domain);
  }

  @Override
  public Page<OwnerView> map(Page<Owner> page) {
    return ownerMapper.map(page);
  }
}
