package org.century.scp.spocr.contact.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.century.scp.spocr.contact.models.dto.ContactRoleView;
import org.century.scp.spocr.contact.models.dto.RequestForCreateContactRole;
import org.century.scp.spocr.contact.models.dto.RequestForUpdateContactRole;
import org.century.scp.spocr.contact.services.ContactRoleServiceFacade;
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
@RequestMapping("/api/contact-roles")
@RequiredArgsConstructor
public class ContactRoleController {

  private final ContactRoleServiceFacade contactRoleService;

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateContactRole contactRole) {
    return ResponseEntity.ok(contactRoleService.create(contactRole));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable(value = "id") long id,
      @Validated @RequestBody RequestForUpdateContactRole patch) {
    contactRoleService.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContactRoleView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(contactRoleService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ContactRoleView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<ContactRole> contactRoleSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(contactRoleService.get(contactRoleSpecification, pageable));
  }
}

