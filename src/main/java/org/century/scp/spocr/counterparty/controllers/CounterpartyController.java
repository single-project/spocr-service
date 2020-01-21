package org.century.scp.spocr.counterparty.controllers;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.base.models.dto.PageResponse;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
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
@RequestMapping("/api/counterparties")
public class CounterpartyController {

  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public CounterpartyController(CounterpartyServiceImpl counterpartyService) {
    this.counterpartyService = counterpartyService;
  }

  @PostMapping
  public ResponseEntity<CounterpartyView> addItem(@RequestBody Counterparty sporItem) {
    return ResponseEntity.ok(counterpartyService.create(sporItem).map());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CounterpartyView> updateItem(
      @PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(counterpartyService.update(id, data).map());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CounterpartyView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(counterpartyService.get(id).map());
  }

  @GetMapping
  public ResponseEntity<PageResponse<Counterparty, CounterpartyView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
            @Spec(path = "active", params = "active", spec = Equal.class)
          })
          Specification<Counterparty> counterpartySpecification,
      Pageable pageable) {
    Page<Counterparty> page =
        counterpartyService.getBySpecification(counterpartySpecification, pageable);
    return ResponseEntity.ok(new PageResponse<>(page));
  }
}
