package org.century.scp.spocr.address.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.address.services.AddressServiceImpl;
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
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

  private final AddressMapper addressMapper;
  private final AddressServiceImpl addressService;

  @GetMapping
  public ResponseEntity<Page<AddressView>> getItems(
      @And({
          @Spec(path = "address", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<Address> addressSpecification,
      Pageable pageable) {
    Page<Address> page = addressService.getBySpecification(addressSpecification, pageable);
    return ResponseEntity.ok(addressMapper.map(page));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(addressMapper.map(addressService.get(id)));
  }
}
