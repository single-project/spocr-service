package org.century.scp.spocr.shop.mappers;

import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.classifier.saleschannel.mappers.SalesChannelMapper;
import org.century.scp.spocr.classifier.shoptype.mappers.ShopTypeMapper;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(
    uses = {
        CounterpartyMapper.class,
        ShopTypeMapper.class,
        SalesChannelMapper.class,
        AddressMapper.class
    })
public interface ShopMapper {

  ShopView map(Shop entity);

  Shop map(ShopView view);

  default Page<ShopView> map(Page<Shop> page) {
    return page.map(this::map);
  }
}
