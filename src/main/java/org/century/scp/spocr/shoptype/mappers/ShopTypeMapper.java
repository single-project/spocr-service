package org.century.scp.spocr.shoptype.mappers;

import java.util.List;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = ManufacturerMapper.class)
public interface ShopTypeMapper {

  ShopType map(ShopTypeView view);

  ShopTypeView map(ShopType entity);

  List<ShopTypeView> map(List<ShopType> entities);

  default Page<ShopTypeView> map(Page<ShopType> page) {
    return page.map(this::map);
  }
}
