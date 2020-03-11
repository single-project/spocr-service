package org.century.scp.spocr.shop.mappers;

import org.century.scp.spocr.extregsystem.mappers.ExtRegSystemMapper;
import org.century.scp.spocr.shop.models.domain.ExtRegSystemShopProperties;
import org.century.scp.spocr.shop.models.dto.ExtRegSystemShopPropertiesView;
import org.mapstruct.Mapper;

@Mapper(uses = ExtRegSystemMapper.class)
public interface ExtRegSystemShopPropertiesMapper {

  ExtRegSystemShopProperties map(ExtRegSystemShopPropertiesView view);

  ExtRegSystemShopPropertiesView map(ExtRegSystemShopProperties entity);
}
