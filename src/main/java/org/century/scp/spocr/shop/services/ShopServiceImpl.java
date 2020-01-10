package org.century.scp.spocr.shop.services;

import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.exceptions.SpocrException;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.repositories.ShopRepository;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopServiceImpl extends BaseService<Shop> {

  private ShopTypesServiceImpl shopTypesService;

  @Autowired
  public ShopServiceImpl(ShopRepository shopRepository, ShopTypesServiceImpl shopTypesService) {
    super(shopRepository);
    this.shopTypesService = shopTypesService;
  }

  public Shop addShopType(Long id, ShopType shopType) {
    Shop shop = get(id);
    ShopType st = shopTypesService.get(shopType.getId());
    shop.addShopType(st);
    return entityRepository.save(shop);
  }

  @Override
  public Shop update(Long id, String data) {
    Shop shop = get(id);
    try {
      shop = mergePatch(shop, data, getEntityClass());
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }

    List<ShopType> shopTypes = shop.getShopTypes();
    if (shopTypes!=null && shopTypes.size()>0) {
      shop.setShopTypes(shopTypesService.getAll(shopTypes));
    }
    return entityRepository.save(shop);
  }

  @Override
  public Class<Shop> getEntityClass() {
    return Shop.class;
  }

  @Override
  public String getEntityName() {
    return "магазин";
  }

}
