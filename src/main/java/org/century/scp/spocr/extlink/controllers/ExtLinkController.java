package org.century.scp.spocr.extlink.controllers;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.extlink.models.domain.ExtLink;
import org.century.scp.spocr.extlink.services.ExtLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/extlinks")
public class ExtLinkController {
  private ExtLinkServiceImpl extLinkService;

  @Autowired
  public ExtLinkController(ExtLinkServiceImpl extLinkService) {
    this.extLinkService = extLinkService;
  }

  @PostMapping
  public ResponseEntity<ExtLink> addItem(@RequestBody ExtLink sporItem) {
    return ResponseEntity.ok(extLinkService.create(sporItem));
  }

  @GetMapping
  public ResponseEntity<List<ExtLink>> getItems(
      @And({
            @Spec(path = "entityExtId", params = "ext-id", spec = Equal.class),
            @Spec(path = "entityType", params = "type", spec = Equal.class),
            @Spec(path = "extProgId", params = "prog", spec = Equal.class)
          })
          Specification<ExtLink> extLinkSpecification) {
    return ResponseEntity.ok(extLinkService.getBySpecification(extLinkSpecification));
  }

  @GetMapping("/available-entity-types")
  public ResponseEntity<List<String>> getEntityTypes() {
    return ResponseEntity.ok(EntityType.getTypes());
  }
}
