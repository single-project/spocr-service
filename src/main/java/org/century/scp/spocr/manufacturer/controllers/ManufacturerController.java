package org.century.scp.spocr.manufacturer.controllers;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
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
@RequestMapping("/api/manufactures")
public class ManufacturerController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private ManufacturerServiceImpl manufacturerService;

  @Autowired
  public ManufacturerController(ManufacturerServiceImpl manufacturerService) {
    this.manufacturerService = manufacturerService;
  }

  @PostMapping
  public ResponseEntity<Manufacturer> addItem(@RequestBody Manufacturer manufacturer) {
    return ResponseEntity.ok(manufacturerService.create(manufacturer));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Manufacturer> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(manufacturerService.update(id, data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Manufacturer> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(manufacturerService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<Manufacturer>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Manufacturer> manufacturerSpecification,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    return ResponseEntity.ok(
        manufacturerService.getBySpecification(manufacturerSpecification, pageable));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Manufacturer>> getItems() {
    return ResponseEntity.ok(manufacturerService.getAll());
  }
}
