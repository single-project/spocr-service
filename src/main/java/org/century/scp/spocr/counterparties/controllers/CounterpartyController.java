package org.century.scp.spocr.counterparties.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.services.CounterpartyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/counterparties")
public class CounterpartyController {

  private static final String DEFAULT_SORT_FIELD = "name";
  private static final int DEFAULT_PAGE_SIZE = 200000;

  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public CounterpartyController(CounterpartyServiceImpl counterpartyService) {
    this.counterpartyService = counterpartyService;
  }

  @PostMapping
  public ResponseEntity<Counterparty> addItem(@RequestBody Counterparty sporItem) {
    return ResponseEntity.ok(counterpartyService.add(sporItem));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Counterparty> updateItem(@PathVariable Long id, @RequestBody String data) {
    return ResponseEntity.ok(counterpartyService.update(id, data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Counterparty> getItem(@PathVariable(value = "id") long id) {
    return ResponseEntity.ok(counterpartyService.get(id));
  }

  @GetMapping
  public ResponseEntity<Page<Counterparty>> getItems(
      @RequestParam(value = "q", defaultValue = "", required = false) String q,
      @RequestParam(value = "active", defaultValue = "*", required = false) String active,
      @PageableDefault(size = DEFAULT_PAGE_SIZE)
          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
          Pageable pageable) {
    Map<String, Object> params = new HashMap<>();
    params.put("q", StringUtils.stripToNull(q));
    params.put("active", BooleanUtils.toBooleanObject(active));
    params.put("page", pageable);
    return ResponseEntity.ok(counterpartyService.getByParams(params));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Counterparty>> getItems() {
    return ResponseEntity.ok(counterpartyService.getByParams());
  }
}
