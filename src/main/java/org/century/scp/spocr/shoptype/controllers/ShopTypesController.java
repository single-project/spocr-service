package org.century.scp.spocr.shoptype.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.century.scp.spocr.shoptype.mappers.ShopTypeMapper;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.RequestForCreateShopType;
import org.century.scp.spocr.shoptype.models.dto.RequestForUpdateShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shoptypes")
@RequiredArgsConstructor
public class ShopTypesController {

  private final ShopTypeMapper shopTypeMapper;
  private final ShopTypesServiceImpl shopTypesService;
  private final ManufacturerServiceImpl manufacturerService;

  @PostMapping
  public ResponseEntity<ShopTypeView> addItem(
      @Validated @RequestBody RequestForCreateShopType shopType) {
    ShopType st = shopTypeMapper.map(shopType);
    Manufacturer manufacturer = manufacturerService.get(shopType.getManufacturer().getId());
    st.setManufacturer(manufacturer);
    return ResponseEntity.ok(shopTypeMapper.map(shopTypesService.create(st)));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopTypeView> updateItem(@PathVariable Long id,
      @RequestBody RequestForUpdateShopType patch) {
    return ResponseEntity
        .ok(shopTypeMapper.map(shopTypesService.update(id, shopTypeMapper.map(patch))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopTypeView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopTypeMapper.map(shopTypesService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ShopTypeView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<ShopType> shopTypeSpecification,
      Pageable pageable) {
    Page<ShopType> page = shopTypesService.getBySpecification(shopTypeSpecification, pageable);
    return ResponseEntity.ok(shopTypeMapper.map(page));
  }
}
