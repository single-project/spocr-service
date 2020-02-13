package org.century.scp.spocr.shop.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.shop.mappers.ShopMapper;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.RequestForCreateShop;
import org.century.scp.spocr.shop.models.dto.RequestForUpdateShop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.century.scp.spocr.shop.services.ShopServiceImpl;
import org.century.scp.spocr.shoptype.mappers.ShopTypeMapper;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

  private final ShopServiceImpl shopService;
  private final ShopMapper shopMapper;
  private final ShopTypeMapper shopTypeMapper;
  private final ShopTypesServiceImpl shopTypesService;
  private final CounterpartyServiceImpl counterpartyService;

  @PutMapping("/{id}")
  public ResponseEntity<ShopView> putShopTypeToShop(
      @PathVariable Long id, @RequestBody ShopType shopType) {
    return ResponseEntity.ok(shopMapper.map(shopService.addShopType(id, shopType)));
  }

  @PostMapping
  public ResponseEntity<ShopView> addItem(@Validated @RequestBody RequestForCreateShop shop) {
    // ******** start *********
    // TODO: вынести все в сервис
    /*Counterparty counterparty = counterpartyService.get(shop.getCounterparty().getId());
    Shop s = shopMapper.map(shop);
    s.setCounterparty(counterparty);

    // TODO: добавить вменяемую валидацию нового адреса
    if (s.getAddress() != null) {
      s.getAddress().setId(null);
    }

    if (s.linkedWithShopTypes()) {
      List<ShopType> shopTypes = shopTypesService.getAll(s.getShopTypes());
      s.setShopTypes(shopTypes);
    }*/
    // ******** end *********
    return ResponseEntity.ok(shopMapper.map(shopService.create(shopMapper.map(shop))));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopView> updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateShop patch) {
    return ResponseEntity.ok(shopMapper.map(shopService.update(id, shopMapper.map(patch))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((shopMapper.map(shopService.get(id))));
  }

  @GetMapping
  public ResponseEntity<Page<ShopView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "counterparty.id", params = "counterparty", spec = Equal.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Shop> shopSpecification,
      Pageable pageable) {
    Page<Shop> page = shopService.getBySpecification(shopSpecification, pageable);
    return ResponseEntity.ok(shopMapper.map(page));
  }
}
