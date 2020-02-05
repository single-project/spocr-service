package org.century.scp.spocr.manufacturer.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.manufacturer.models.dto.RequestForCreateManufacturer;
import org.century.scp.spocr.manufacturer.models.dto.RequestForUpdateManufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
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
@RequestMapping("/api/manufactures")
@RequiredArgsConstructor
public class ManufacturerController {

  private final ManufacturerMapper manufacturerMapper;
  private final ManufacturerServiceImpl manufacturerService;

  @PostMapping
  public ResponseEntity<ManufacturerView> addItem(
      @Validated @RequestBody RequestForCreateManufacturer manufacturer) {
    return ResponseEntity.ok(
        manufacturerMapper.map((manufacturerService.create(manufacturerMapper.map(manufacturer)))));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ManufacturerView> updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateManufacturer patch) {
    return ResponseEntity
        .ok(manufacturerMapper.map(manufacturerService.update(id, manufacturerMapper.map(patch))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ManufacturerView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(manufacturerMapper.map(manufacturerService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ManufacturerView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Manufacturer> manufacturerSpecification,
      Pageable pageable) {
    Page<Manufacturer> page =
        manufacturerService.getBySpecification(manufacturerSpecification, pageable);

    return ResponseEntity.ok(manufacturerMapper.map(page));
  }
}
