package org.century.scp.spocr.counterparty.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.counterparty.models.dto.RequestForCreateCounterparty;
import org.century.scp.spocr.counterparty.models.dto.RequestForUpdateCounterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceFacade;
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
@RequestMapping("/api/counterparties")
@RequiredArgsConstructor
public class CounterpartyController {

  private final CounterpartyServiceFacade counterpartyService;

  @PostMapping
  public ResponseEntity<CounterpartyView> addItem(
      @Validated @RequestBody RequestForCreateCounterparty counterparty) {
    return ResponseEntity.ok(counterpartyService.create(counterparty));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CounterpartyView> updateItem(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdateCounterparty patch) {
    return ResponseEntity.ok(counterpartyService.update(id, patch, patch.getUpdatedFields()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CounterpartyView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(counterpartyService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<CounterpartyView>> getItems(
      @And({
            @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class),
          @Spec(path = "owner.id", params = "owner.id", spec = Equal.class),
          @Spec(path = "owner.name", params = "owner.name", spec = Equal.class),
          })
          Specification<Counterparty> counterpartySpecification,
      Pageable pageable) {
    return ResponseEntity.ok(counterpartyService.get(counterpartySpecification, pageable));
  }
}
