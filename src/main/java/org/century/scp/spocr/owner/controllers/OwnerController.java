package org.century.scp.spocr.owner.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.century.scp.spocr.owner.models.dto.RequestForCreateOwner;
import org.century.scp.spocr.owner.models.dto.RequestForUpdateOwner;
import org.century.scp.spocr.owner.services.OwnerServiceFacade;
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
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

  private final OwnerServiceFacade ownerService;

  @PostMapping
  public ResponseEntity<OwnerView> addItem(
      @Validated @RequestBody RequestForCreateOwner owner) {
    return ResponseEntity.ok(ownerService.create(owner));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OwnerView> updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateOwner patch) {
    return ResponseEntity.ok(ownerService.update(id, patch, patch.getUpdatedFields()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OwnerView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(ownerService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<OwnerView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<Owner> ownerSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(ownerService.get(ownerSpecification, pageable));
  }
}
