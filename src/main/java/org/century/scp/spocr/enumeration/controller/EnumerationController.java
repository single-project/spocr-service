package org.century.scp.spocr.enumeration.controller;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.century.scp.spocr.enumeration.services.EnumerationServiceFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/enumerations")
@RequiredArgsConstructor
public class EnumerationController {

  private final EnumerationServiceFacade enumerationService;

  @GetMapping("/{id}")
  public ResponseEntity<EnumerationView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((enumerationService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<EnumerationView>> getItems(
      @And({@Spec(path = "ident", params = "ident", spec = LikeIgnoreCase.class)})
          Specification<Enumeration> enumerationSpecification,
      @PageableDefault(sort = "ident") Pageable pageable) {
    return ResponseEntity.ok(enumerationService.get(enumerationSpecification, pageable));
  }
}
