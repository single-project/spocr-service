package org.century.scp.spocr.contact.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.century.scp.spocr.contact.models.dto.RequestForCreateContact;
import org.century.scp.spocr.contact.models.dto.RequestForUpdateContact;
import org.century.scp.spocr.contact.services.ContactServiceFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

  private final ContactServiceFacade contactService;

  @PostMapping
  public ResponseEntity<ContactView> addItem(
      @Validated @RequestBody RequestForCreateContact contact) {
    return ResponseEntity.ok(contactService.create(contact));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ContactView> updateItem(
      @PathVariable(value = "id") long id,
      @Validated @RequestBody RequestForUpdateContact patch) {
    return ResponseEntity.ok(contactService.update(id, patch, patch.getUpdatedFields()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContactView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(contactService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<ContactView>> getItems(
      @And({
          @Spec(path = "person.name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "person.id", params = "person.id", spec = Equal.class),
          @Spec(path = "person.name", params = "person.name", spec = LikeIgnoreCase.class),
          @Spec(path = "role.id", params = "role.id", spec = Equal.class),
          @Spec(path = "role.name", params = "role.name", spec = LikeIgnoreCase.class)
      })
          Specification<Contact> contactSpecification,
      @PageableDefault(sort = "person.lastName") Pageable pageable) {
    return ResponseEntity.ok(contactService.get(contactSpecification, pageable));
  }
}
