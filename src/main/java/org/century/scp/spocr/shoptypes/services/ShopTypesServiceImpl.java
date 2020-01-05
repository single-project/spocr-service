package org.century.scp.spocr.shoptypes.services;

import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.events.services.AuditableEntityServiceImpl;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.repositories.ShopTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopTypesServiceImpl extends AuditableEntityServiceImpl {

  private ShopTypeRepository shopTypeRepository;

  @Autowired
  public ShopTypesServiceImpl(ShopTypeRepository shopTypeRepository) {
    this.shopTypeRepository = shopTypeRepository;
  }

  public ShopType get(long id) {
    return shopTypeRepository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(id, "тип магазина"));
  }

  public ShopType create(ShopType item) {
    return shopTypeRepository.save(item);
  }

  public ShopType update(Long id, String data) {
    ShopType shopType = get(id);
    try {
      shopType = mergePatch(shopType, data, ShopType.class);
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    return shopTypeRepository.save(shopType);
  }

  public Page<ShopType> getByParams(Map<String, Object> params) {
    String q = params.get("q") == null ? null : (String) params.get("q");
    Boolean active = params.get("active") == null ? null : (Boolean) params.get("active");
    Pageable pageable = (Pageable) params.get("page");
    return shopTypeRepository.search(q, active, pageable);
  }

  public List<ShopType> getAll() {
    return shopTypeRepository.findAll();
  }
}
