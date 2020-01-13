package org.century.scp.spocr.counterparty.controllers;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
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
@RequestMapping("/api/counterparties")
public class CounterpartyController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public CounterpartyController(CounterpartyServiceImpl counterpartyService) {
    this.counterpartyService = counterpartyService;
  }

  @PostMapping
  public ResponseEntity<Counterparty> addItem(@RequestBody Counterparty sporItem) {
    return ResponseEntity.ok(counterpartyService.create(sporItem));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Counterparty> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(counterpartyService.update(id, data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Counterparty> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(counterpartyService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<Counterparty>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Counterparty> counterpartySpecification,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    return ResponseEntity.ok(
        counterpartyService.getBySpecification(counterpartySpecification, pageable));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Counterparty>> getItems() {
    return ResponseEntity.ok(counterpartyService.getAll());
  }
}