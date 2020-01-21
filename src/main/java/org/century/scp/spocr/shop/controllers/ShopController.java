package org.century.scp.spocr.shop.controllers;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.base.models.dto.PageResponse;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.century.scp.spocr.shop.services.ShopServiceImpl;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
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
public class ShopController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private ShopServiceImpl shopService;
  private ShopTypesServiceImpl shopTypesService;
  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public ShopController(
      ShopServiceImpl shopService,
      ShopTypesServiceImpl shopTypesService,
      CounterpartyServiceImpl counterpartyService) {
    this.shopService = shopService;
    this.shopTypesService = shopTypesService;
    this.counterpartyService = counterpartyService;
  }

  @PutMapping("/{id}")
  public ResponseEntity<ShopView> putShopTypeToShop(
      @PathVariable Long id, @RequestBody ShopType shopType) {
    return ResponseEntity.ok(shopService.addShopType(id, shopType).map());
  }

  @PostMapping
  public ResponseEntity<ShopView> addItem(@RequestBody Shop shop) {
    Counterparty counterparty = counterpartyService.get(shop.getCounterparty().getId());
    List<ShopType> shopTypes = shopTypesService.getAll(shop.getShopTypes());
    shop.setCounterparty(counterparty);
    shop.setShopTypes(shopTypes);
    return ResponseEntity.ok(shopService.create(shop).map());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopView> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(shopService.update(id, data).map());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((shopService.get(id).map()));
  }

  @GetMapping
  public ResponseEntity<PageResponse<Shop, ShopView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Shop> shopSpecification,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    Page<Shop> page = shopService.getBySpecification(shopSpecification, pageable);
    PageResponse<Shop, ShopView> pageResponse = new PageResponse<>(page);
    return ResponseEntity.ok(pageResponse);
  }

}
