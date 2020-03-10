package org.century.scp.spocr.person.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.person.models.domain.Person;
import org.century.scp.spocr.person.models.dto.PersonView;
import org.century.scp.spocr.person.models.dto.RequestForCreatePerson;
import org.century.scp.spocr.person.models.dto.RequestForUpdatePerson;
import org.century.scp.spocr.person.services.PersonServiceFacade;
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
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

  private final PersonServiceFacade personService;

  @PostMapping
  public ResponseEntity<Long> addItem(@Validated @RequestBody RequestForCreatePerson person) {
    return ResponseEntity.ok(personService.create(person));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdatePerson patch) {
    personService.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((personService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<PersonView>> getItems(
      @And({
          @Spec(path = "lastName", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
          @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
          @Spec(path = "patronymic", params = "patronymic", spec = LikeIgnoreCase.class),
          @Spec(path = "inn", params = "inn", spec = LikeIgnoreCase.class),
          @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<Person> personSpecification,
      @PageableDefault(sort = "lastName") Pageable pageable) {
    return ResponseEntity.ok(personService.get(personSpecification, pageable));
  }
}
