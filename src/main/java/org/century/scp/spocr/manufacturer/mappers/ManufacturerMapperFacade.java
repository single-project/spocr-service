package org.century.scp.spocr.manufacturer.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ManufacturerMapperFacade implements MapperI<Manufacturer, ManufacturerView> {

  private final ManufacturerMapper mapper;

  @Override
  public Manufacturer map(ManufacturerView dto) {
    return mapper.map(dto);
  }

  @Override
  public ManufacturerView map(Manufacturer domain) {
    return mapper.map(domain);
  }

  @Override
  public Page<ManufacturerView> map(Page<Manufacturer> page) {
    return mapper.map(page);
  }
}
