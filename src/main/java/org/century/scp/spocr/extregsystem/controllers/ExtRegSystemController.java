package org.century.scp.spocr.extregsystem.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;
import org.century.scp.spocr.extregsystem.models.dto.RequestForCreateExtRegSystem;
import org.century.scp.spocr.extregsystem.models.dto.RequestForUpdateExtRegSystem;
import org.century.scp.spocr.extregsystem.services.ExtRegSystemServiceFacade;
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
@RequestMapping("/api/ext-reg-systems")
@RequiredArgsConstructor
public class ExtRegSystemController {

  private final ExtRegSystemServiceFacade extRegSystemService;

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateExtRegSystem extRegSystem) {
    return ResponseEntity.ok(extRegSystemService.create(extRegSystem));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdateExtRegSystem patch) {
    extRegSystemService.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExtRegSystemView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((extRegSystemService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ExtRegSystemView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class),
      })
          Specification<ExtRegSystem> extRegSystemSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(extRegSystemService.get(extRegSystemSpecification, pageable));
  }
}
