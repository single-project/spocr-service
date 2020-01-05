package org.century.scp.spocr.shops.services;

import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.events.services.AuditableEntityServiceImpl;
import org.century.scp.spocr.exceptions.SpocrException;
import org.century.scp.spocr.shops.models.domain.Shop;
import org.century.scp.spocr.shops.repositories.ShopRepository;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopServiceImpl extends AuditableEntityServiceImpl {

  private ShopRepository shopRepository;
  private ShopTypesServiceImpl shopTypesService;

  @Autowired
  public ShopServiceImpl(ShopRepository shopRepository,
      ShopTypesServiceImpl shopTypesService) {
    this.shopRepository = shopRepository;
    this.shopTypesService = shopTypesService;
  }

  public Shop get(long id) {
    return shopRepository
        .findById(id)
        .orElseThrow(() -> new SpocrException("Магазин с кодом " + id + " не найден"));
  }

  public Shop create(Shop item) {
    return shopRepository.save(item);
  }

  public Shop addShopType(Long id, ShopType shopType) {
    Shop shop = get(id);
    ShopType st = shopTypesService.get(shopType.getId());
    shop.addShopType(st);
    return shopRepository.save(shop);
  }

  public Shop update(Long id, String data) {
    Shop shop = get(id);
    try {
      shop = mergePatch(shop, data, Shop.class);
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    return shopRepository.save(shop);
  }

  public Page<Shop> getByParams(Map<String, Object> params) {
    String q = params.get("q") == null ? null : (String) params.get("q");
    Boolean active = params.get("active") == null ? null : (Boolean) params.get("active");
    Pageable pageable = (Pageable) params.get("page");
    return shopRepository.search(q, active, pageable);
  }

  public List<Shop> getAll() {
    return shopRepository.findAll();
  }
}
