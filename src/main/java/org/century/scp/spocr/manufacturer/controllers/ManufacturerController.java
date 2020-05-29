package org.century.scp.spocr.manufacturer.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.manufacturer.models.dto.RequestForCreateManufacturer;
import org.century.scp.spocr.manufacturer.models.dto.RequestForUpdateManufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceFacade;
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
@RequestMapping("/api/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

  private final ManufacturerServiceFacade manufacturerService;

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateManufacturer manufacturer) {
    return ResponseEntity.ok(manufacturerService.create(manufacturer));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateManufacturer patch) {
    manufacturerService.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ManufacturerView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(manufacturerService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ManufacturerView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Manufacturer> manufacturerSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(manufacturerService.get(manufacturerSpecification, pageable));
  }
}
