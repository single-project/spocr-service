package org.century.scp.spocr.manufactures.services;

import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.events.services.AuditableEntityServiceImpl;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.century.scp.spocr.manufactures.models.domain.Manufacturer;
import org.century.scp.spocr.manufactures.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManufacturerServiceImpl extends AuditableEntityServiceImpl {

  private ManufacturerRepository manufacturerRepository;

  @Autowired
  public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
    this.manufacturerRepository = manufacturerRepository;
  }

  public Manufacturer get(long id) {
    return manufacturerRepository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(id, "производитель"));
  }

  public Manufacturer create(Manufacturer item) {
    return manufacturerRepository.save(item);
  }

  public Manufacturer update(Long id, String data) {
    Manufacturer manufacturer = get(id);
    try {
      manufacturer = mergePatch(manufacturer, data, Manufacturer.class);
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    return manufacturerRepository.save(manufacturer);
  }

  public Page<Manufacturer> getByParams(Map<String, Object> params) {
    String q = params.get("q") == null ? null : (String) params.get("q");
    Boolean active = params.get("active") == null ? null : (Boolean) params.get("active");
    Pageable pageable = (Pageable) params.get("page");
    return manufacturerRepository.search(q, active, pageable);
  }

  public List<Manufacturer> getAll() {
    return manufacturerRepository.findAll();
  }
}
