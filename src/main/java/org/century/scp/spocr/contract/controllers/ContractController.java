package org.century.scp.spocr.contract.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.models.dto.ContractView;
import org.century.scp.spocr.contract.models.dto.RequestForCreateContract;
import org.century.scp.spocr.contract.models.dto.RequestForCreateSubContract;
import org.century.scp.spocr.contract.models.dto.RequestForUpdateContract;
import org.century.scp.spocr.contract.models.dto.RequestForUpdateSubContract;
import org.century.scp.spocr.contract.models.dto.SubContractView;
import org.century.scp.spocr.contract.services.ContractServiceFacade;
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
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

  private final ContractServiceFacade contractServiceFacade;

  @GetMapping("/{id}")
  public ResponseEntity<ContractView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok((contractServiceFacade.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ContractView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "counterparty1.id", params = "counterparty1.id", spec = Equal.class),
          @Spec(path = "counterparty2.id", params = "counterparty2.id", spec = Equal.class),
          @Spec(
              path = "counterparty1.name",
              params = "counterparty1.name",
              spec = LikeIgnoreCase.class),
          @Spec(
              path = "counterparty2.name",
              params = "counterparty2.name",
              spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<Contract> contractSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(contractServiceFacade.get(contractSpecification, pageable));
  }

  @GetMapping("/{id}/subcontracts")
  public ResponseEntity<Page<SubContractView>> getSubContracts(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "contract.id", params = "contract.id", spec = Equal.class),
          @Spec(
              path = "subContractNumber",
              params = "subContractNumber",
              spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<SubContract> subContractSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(
        contractServiceFacade.getSubContracts(subContractSpecification, pageable));
  }

  @PostMapping
  public ResponseEntity<Long> addItem(
      @Validated @RequestBody RequestForCreateContract createContractRequest) {
    return ResponseEntity.ok(contractServiceFacade.create(createContractRequest));
  }

  @PostMapping("/{id}/subcontracts")
  public ResponseEntity<Long> addSubContract(
      @Validated @RequestBody RequestForCreateSubContract createContractRequest) {
    return ResponseEntity.ok(contractServiceFacade.createSubContract(createContractRequest));
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateItem(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdateContract patch) {
    contractServiceFacade.update(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}/subcontracts/{id}")
  public ResponseEntity updateSubContract(
      @PathVariable Long id, @Validated @RequestBody RequestForUpdateSubContract patch) {
    contractServiceFacade.updateSubContract(id, patch, patch.getUpdatedFields());
    return ResponseEntity.ok().build();
  }
}
