package org.century.scp.spocr.shoptype.controllers;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<ShopType> shopTypeSpecification,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
      @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    return ResponseEntity.ok(
        shopTypesService.getBySpecification(shopTypeSpecification, pageable));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<ShopType>> getItems() {
    return ResponseEntity.ok(shopTypesService.getAll());
  }
}
