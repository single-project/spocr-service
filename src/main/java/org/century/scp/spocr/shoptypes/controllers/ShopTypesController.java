package org.century.scp.spocr.shoptypes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.century.scp.spocr.manufactures.models.domain.Manufacturer;
import org.century.scp.spocr.manufactures.services.ManufacturerServiceImpl;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.services.ShopTypesServiceImpl;
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
@RequestMapping("/api/shoptypes")
public class ShopTypesController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private ShopTypesServiceImpl shopTypesService;
  private ManufacturerServiceImpl manufacturerService;

  @Autowired
  public ShopTypesController(ShopTypesServiceImpl shopTypesService,
      ManufacturerServiceImpl manufacturerService) {
    this.shopTypesService = shopTypesService;
    this.manufacturerService = manufacturerService;
  }

  @PostMapping
  public ResponseEntity<ShopType> addItem(@RequestBody ShopType shopType) {
    Manufacturer manufacturer = manufacturerService.get(shopType.getManufacturer().getId());
    shopType.setManufacturer(manufacturer);
    return ResponseEntity.ok(shopTypesService.create(shopType));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopType> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(shopTypesService.update(id, data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopType> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopTypesService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ShopType>> getItems(
      @RequestParam(value = "q", defaultValue = "", required = false) String q,
      @RequestParam(value = "active", defaultValue = "*", required = false) String active,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
      @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    Map<String, Object> params = new HashMap<>();
    params.put("q", StringUtils.stripToNull(q));
    params.put("active", BooleanUtils.toBooleanObject(active));
    params.put("page", pageable);
    return ResponseEntity.ok(shopTypesService.getByParams(params));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<ShopType>> getItems() {
    return ResponseEntity.ok(shopTypesService.getAll());
  }
}