package org.century.scp.spocr.manufacturer.mappers;

import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.manufacturer.models.dto.RequestForCreateUpdateManufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper
public interface ManufacturerMapper {

  @Mapping(target = "updatedFields", ignore = true)
  ManufacturerView map(Manufacturer entity);

  Manufacturer map(ManufacturerView view);

  Manufacturer map(RequestForCreateUpdateManufacturer request);

  default Page<ManufacturerView> map(Page<Manufacturer> page) {
    return page.map(this::map);
  }
}
