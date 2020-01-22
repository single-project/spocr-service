package org.century.scp.spocr.shoptype.controllers;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.base.models.dto.PageResponse;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

  private ShopTypesServiceImpl shopTypesService;
  private ManufacturerServiceImpl manufacturerService;

  @Autowired
  public ShopTypesController(
      ShopTypesServiceImpl shopTypesService, ManufacturerServiceImpl manufacturerService) {
    this.shopTypesService = shopTypesService;
    this.manufacturerService = manufacturerService;
  }

  @PostMapping
  public ResponseEntity<ShopTypeView> addItem(@RequestBody ShopType shopType) {
    Manufacturer manufacturer = manufacturerService.get(shopType.getManufacturer().getId());
    shopType.setManufacturer(manufacturer);
    return ResponseEntity.ok(shopTypesService.create(shopType).map());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopTypeView> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(shopTypesService.update(id, data).map());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopTypeView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopTypesService.get(id).map());
  }

  @GetMapping
  public ResponseEntity<PageResponse<ShopTypeView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<ShopType> shopTypeSpecification,
      Pageable pageable) {
    Page<ShopType> page = shopTypesService.getBySpecification(shopTypeSpecification, pageable);
    return ResponseEntity.ok(new PageResponse<>(page));
  }
}
