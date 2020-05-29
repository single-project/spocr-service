package org.century.scp.spocr.classifier.shopdepart.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.models.dto.RequestForCreateClassifier;
import org.century.scp.spocr.classifier.models.dto.RequestForUpdateClassifier;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.century.scp.spocr.classifier.shopdepart.services.ShopDepartServiceFacade;
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
@RequestMapping("/api/shop-departs")
@RequiredArgsConstructor
public class ShopDepartController {

  private final ShopDepartServiceFacade shopDepartService;

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateClassifier shopType) {
    return ResponseEntity.ok(shopDepartService.create(shopType));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateClassifier patch) {
    shopDepartService.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClassifierView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(shopDepartService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ClassifierView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "manufacturer.id", params = "manufacturer.id", spec = Equal.class),
          @Spec(
              path = "manufacturer.name",
              params = "manufacturer.name",
              spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<ShopDepart> shopDepartSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(shopDepartService.get(shopDepartSpecification, pageable));
  }
}
