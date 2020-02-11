package org.century.scp.spocr.extlink.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.extlink.mappers.ExtLinkMapper;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.extlink.models.domain.ExtLink;
import org.century.scp.spocr.extlink.models.dto.ExtLinkView;
import org.century.scp.spocr.extlink.models.dto.RequestForCreateExtLink;
import org.century.scp.spocr.extlink.services.ExtLinkServiceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/extlinks")
@RequiredArgsConstructor
public class ExtLinkController {

  private final ExtLinkMapper extLinkMapper;
  private final ExtLinkServiceImpl extLinkService;

  @PostMapping
  public ResponseEntity<ExtLinkView> addItem(
      @Validated @RequestBody RequestForCreateExtLink sporItem) {
    return ResponseEntity.ok(extLinkMapper.map(extLinkService.create(extLinkMapper.map(sporItem))));
  }

  @GetMapping
  public ResponseEntity<List<ExtLinkView>> getItems(
      @And({
            @Spec(path = "entityId", params = "id", spec = Equal.class),
            @Spec(path = "entityExtId", params = "ext-id", spec = Equal.class),
            @Spec(path = "entityType", params = "type", spec = Equal.class),
            @Spec(path = "extProgId", params = "prog", spec = Equal.class)
          })
          Specification<ExtLink> extLinkSpecification) {
    return ResponseEntity.ok(
        extLinkMapper.map(extLinkService.getBySpecification(extLinkSpecification)));
  }

  @GetMapping("/available-entity-types")
  public ResponseEntity<List<String>> getEntityTypes() {
    return ResponseEntity.ok(EntityType.getTypes());
  }
}
