package org.century.scp.spocr.classifier.shoptype.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.models.dto.RequestForCreateClassifier;
import org.century.scp.spocr.classifier.models.dto.RequestForUpdateClassifier;
import org.century.scp.spocr.classifier.shoptype.mappers.ShopTypeMapper;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.shoptype.services.ShopTypesServiceImpl;
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

  @PostMapping
  public ResponseEntity<ClassifierView> addItem(
      @Validated @RequestBody RequestForCreateClassifier shopType) {
    ShopType st = shopTypeMapper.map(shopType);
    return ResponseEntity.ok(shopTypeMapper.map(shopTypesService.create(st)));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ClassifierView> updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateClassifier patch) {
    return ResponseEntity.ok(
        shopTypeMapper.map(shopTypesService.update(id, shopTypeMapper.map(patch))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClassifierView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopTypeMapper.map(shopTypesService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ClassifierView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(
              path = "manufacturer.id",
              params = "manufacturer.id",
              spec = Equal.class),
          @Spec(
              path = "manufacturer.name",
              params = "manufacturer.name",
              spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<ShopType> shopTypeSpecification,
      Pageable pageable) {
    Page<ShopType> page = shopTypesService.getBySpecification(shopTypeSpecification, pageable);
    return ResponseEntity.ok(shopTypeMapper.map(page));
  }
}
