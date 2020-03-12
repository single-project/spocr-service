package org.century.scp.spocr.accesslevel.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
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
@RequestMapping("/api/access-level")
@RequiredArgsConstructor
public class AccessLevelController {

  private final AccessLevelServiceImpl accessLevelService;

  @GetMapping("/roles")
  public ResponseEntity<List<SystemRole>> getRoles() {
    return ResponseEntity.ok((accessLevelService.getRoles()));
  }

  @GetMapping("/rules")
  public ResponseEntity<List<SystemRule>> getRules(
      @Spec(path = "active", params = "active", spec = Equal.class)
          Specification<SystemRule> ruleSpecification) {
    return ResponseEntity.ok((accessLevelService.getRules(ruleSpecification)));
  }

  @GetMapping("/roles/{id}")
  public ResponseEntity<SystemRole> getRole(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((accessLevelService.getRole(id)));
  }

  @PostMapping("/roles")
  public ResponseEntity<Long> addRole(@Validated @RequestBody SystemRole role) {
    return ResponseEntity.ok(accessLevelService.createRole(role.getName()).getId());
  }

  @PatchMapping("/roles/{id}")
  public ResponseEntity updateRole(@Validated @RequestBody SystemRole role) {
    accessLevelService.updateRole(role);
    return ResponseEntity.ok().build();
  }
}
