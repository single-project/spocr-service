package org.century.scp.spocr.counterparties.services;

import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.repositories.CounterpartyRepository;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyServiceImpl {

  private CounterpartyRepository counterpartyRepository;

  @Autowired
  public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
    this.counterpartyRepository = counterpartyRepository;
  }

  public Counterparty add(Counterparty spocrItem) {
    return counterpartyRepository.save(spocrItem);
  }

  public Counterparty update(Long id, String data) {
    Counterparty counterparty = get(id);
    try {
      counterparty = JsonMergePatchUtils.mergePatch(counterparty, data, Counterparty.class);
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    return counterpartyRepository.save(counterparty);
  }

  public Counterparty get(long id) {
    return counterpartyRepository
        .findById(id)
        .orElseThrow(() -> new SpocrException("Элемент с кодом " + id + " не найден"));
  }

  public Page<Counterparty> getByParams(Map<String, Object> params) {
    String q = params.get("q") == null ? null : (String) params.get("q");
    Boolean active = params.get("active") == null ? null : (Boolean) params.get("active");
    Pageable pageable = (Pageable) params.get("page");
    return counterpartyRepository.search(q, active, pageable);
  }

  public List<Counterparty> getAll() {
    return counterpartyRepository.findAll();
  }
}
