package org.century.scp.spocr.shops.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.services.CounterpartyServiceImpl;
import org.century.scp.spocr.shops.models.domain.Shop;
import org.century.scp.spocr.shops.services.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shops")
public class ShopController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private ShopServiceImpl shopService;
  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public ShopController(ShopServiceImpl shopService,
      CounterpartyServiceImpl counterpartyService) {
    this.shopService = shopService;
    this.counterpartyService = counterpartyService;
  }

  @PostMapping
  public ResponseEntity<Shop> addItem(@RequestBody Shop shop) {
    Counterparty counterparty = counterpartyService.get(shop.getCounterparty().getId());
    shop.setCounterparty(counterparty);
    return ResponseEntity.ok(shopService.create(shop));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Shop> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(shopService.update(id, data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Shop> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<Shop>> getItems(
      @RequestParam(value = "q", defaultValue = "", required = false) String q,
      @RequestParam(value = "active", defaultValue = "*", required = false) String active,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
      @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    Map<String, Object> params = new HashMap<>();
    params.put("q", StringUtils.stripToNull(q));
    params.put("active", BooleanUtils.toBooleanObject(active));
    params.put("page", pageable);
    return ResponseEntity.ok(shopService.getByParams(params));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Shop>> getItems() {
    return ResponseEntity.ok(shopService.getAll());
  }
}
