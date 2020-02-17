package org.century.scp.spocr.shop.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.RequestForCreateShop;
import org.century.scp.spocr.shop.models.dto.RequestForUpdateShop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.century.scp.spocr.shop.services.ShopServiceFacade;
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
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

  private final ShopServiceFacade shopService;

  @PostMapping
  public ResponseEntity<ShopView> addItem(@Validated @RequestBody RequestForCreateShop shop) {
    return ResponseEntity.ok(shopService.create(shop));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShopView> updateItem(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdateShop patch) {
    return ResponseEntity.ok(shopService.update(id, patch, patch.getUpdatedFields()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((shopService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ShopView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "counterparty.id", params = "counterparty.id", spec = Equal.class),
          @Spec(
              path = "counterparty.name",
              params = "counterparty.name",
              spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Shop> shopSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(shopService.get(shopSpecification, pageable));
  }
}
