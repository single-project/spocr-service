package org.century.scp.spocr.manufacturer.controllers;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.base.models.dto.PageResponse;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
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
@RequestMapping("/api/manufactures")
public class ManufacturerController {

  private ManufacturerServiceImpl manufacturerService;

  @Autowired
  public ManufacturerController(ManufacturerServiceImpl manufacturerService) {
    this.manufacturerService = manufacturerService;
  }

  @PostMapping
  public ResponseEntity<ManufacturerView> addItem(@RequestBody Manufacturer manufacturer) {
    return ResponseEntity.ok(manufacturerService.create(manufacturer).map());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ManufacturerView> updateItem(
      @PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(manufacturerService.update(id, data).map());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ManufacturerView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(manufacturerService.get(id).map());
  }

  @GetMapping
  public ResponseEntity<PageResponse<ManufacturerView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Manufacturer> manufacturerSpecification,
      Pageable pageable) {
    Page<Manufacturer> page =
        manufacturerService.getBySpecification(manufacturerSpecification, pageable);
    return ResponseEntity.ok(new PageResponse<>(page));
  }
}
