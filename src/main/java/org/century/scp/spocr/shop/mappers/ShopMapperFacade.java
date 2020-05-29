package org.century.scp.spocr.shop.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopMapperFacade implements MapperI<Shop, ShopView> {

  private final ShopMapper shopMapper;

  @Override
  public Shop map(ShopView dto) {
    return shopMapper.map(dto);
  }

  @Override
  public ShopView map(Shop domain) {
    return shopMapper.map(domain);
  }

  @Override
  public Page<ShopView> map(Page<Shop> page) {
    return shopMapper.map(page);
  }
}
