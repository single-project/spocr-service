package org.century.scp.spocr.draft.controllers;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.draft.models.domain.Draft;
import org.century.scp.spocr.draft.models.dto.DraftView;
import org.century.scp.spocr.draft.models.dto.RequestForCreateDraft;
import org.century.scp.spocr.draft.models.dto.RequestForUpdateDraft;
import org.century.scp.spocr.draft.services.DraftServiceFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/drafts")
@RequiredArgsConstructor
public class DraftController {

  private final DraftServiceFacade draftService;

  @GetMapping("/{id}")
  public ResponseEntity<DraftView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((draftService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<DraftView>> getItems(
      @And({@Spec(path = "username", params = "username", spec = LikeIgnoreCase.class)})
          Specification<Draft> draftSpecification,
      @PageableDefault(sort = "id") Pageable pageable) {
    return ResponseEntity.ok(draftService.get(draftSpecification, pageable));
  }

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateDraft draft) {
    return ResponseEntity.ok(draftService.create(draft));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable(value = "id") long id,
      @Validated @RequestBody RequestForUpdateDraft patch) {
    List<String> properties = new ArrayList<>();
    properties.add("body");
    draftService.update(id, patch, properties);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteItem(@PathVariable(value = "id") long id) {
    draftService.delete(id);
    return ResponseEntity.ok().build();
  }
}
