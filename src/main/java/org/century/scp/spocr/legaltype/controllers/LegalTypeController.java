package org.century.scp.spocr.legaltype.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.legaltype.mappers.LegalTypeMapper;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.legaltype.services.LegalTypeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/legaltypes")
@RequiredArgsConstructor
public class LegalTypeController {

  private final LegalTypeMapper legalTypeMapper;
  private final LegalTypeServiceImpl legalTypeService;

  @GetMapping("/{id}")
  public ResponseEntity<LegalTypeView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(legalTypeMapper.map(legalTypeService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<LegalTypeView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class),
          @Spec(path = "okpfId", params = "opfCode", spec = Equal.class)
      })
          Specification<LegalType> legalTypeSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(
        legalTypeMapper.map(legalTypeService.getBySpecification(legalTypeSpecification, pageable)));
  }
}
