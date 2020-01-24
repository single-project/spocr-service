package org.century.scp.spocr.manufacturer.mappers;

import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface ManufacturerMapper {

  ManufacturerView map(Manufacturer entity);

  Manufacturer map(ManufacturerView view);

  default Page<ManufacturerView> map(Page<Manufacturer> page) {
    return page.map(this::map);
  }
}
