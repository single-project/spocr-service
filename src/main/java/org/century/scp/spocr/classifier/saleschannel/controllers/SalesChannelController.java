package org.century.scp.spocr.classifier.saleschannel.controllers;

import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.models.dto.RequestForCreateClassifier;
import org.century.scp.spocr.classifier.models.dto.RequestForUpdateClassifier;
import org.century.scp.spocr.classifier.saleschannel.mappers.SalesChannelMapper;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.saleschannel.services.SalesChannelServiceImpl;
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
@RequestMapping("/api/saleschannel")
@RequiredArgsConstructor
public class SalesChannelController {

  private final SalesChannelMapper salesChannelMapper;
  private final SalesChannelServiceImpl salesChannelService;

  @PostMapping
  public ResponseEntity<ClassifierView> addItem(
      @Validated @RequestBody RequestForCreateClassifier salesChannel) {
    SalesChannel sc = salesChannelMapper.map(salesChannel);
    return ResponseEntity.ok(salesChannelMapper.map(salesChannelService.create(sc)));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ClassifierView> updateItem(
      @PathVariable Long id, @RequestBody RequestForUpdateClassifier patch) {
    return ResponseEntity.ok(
        salesChannelMapper.map(
            salesChannelService.update(id, salesChannelMapper.map(patch))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClassifierView> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(salesChannelMapper.map(salesChannelService.get(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ClassifierView>> getItems(
      @And({
          @Spec(path = "name", params = "q", spec = LikeIgnoreCase.class),
          @Spec(path = "id", params = "id", spec = Equal.class),
          @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
          @Spec(path = "manufacturer.id", params = "manufacturer", spec = LikeIgnoreCase.class),
          @Spec(path = "active", params = "active", spec = Equal.class)
      })
          Specification<SalesChannel> salesChannelSpecification,
      Pageable pageable) {
    Page<SalesChannel> page = salesChannelService
        .getBySpecification(salesChannelSpecification, pageable);
    return ResponseEntity.ok(salesChannelMapper.map(page));
  }
}
