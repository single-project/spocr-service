package org.century.scp.spocr.paymentdetails.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;
import org.century.scp.spocr.paymentdetails.services.PaymentDetailsServiceFacade;
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
@RequestMapping("/api/paymentdetails")
@RequiredArgsConstructor
public class PaymentDetailsController {

  private final PaymentDetailsServiceFacade paymentDetailsService;

  @GetMapping
  public ResponseEntity<Page<PaymentDetailsView>> getItems(
      @And({
          @Spec(path = "paymentAccount", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "paymentAccount", params = "paymentAccount", spec = Equal.class),
          @Spec(
              path = "correspondingAccount",
              params = "correspondingAccount",
              spec = Equal.class),
          @Spec(path = "bic", params = "bic", spec = Equal.class),
          @Spec(path = "bank", params = "bank", spec = Equal.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<PaymentDetails> detailsSpecification,
      Pageable pageable) {
    return ResponseEntity.ok(paymentDetailsService.get(detailsSpecification, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentDetailsView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(paymentDetailsService.get(id));
  }
}
