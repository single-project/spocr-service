package org.century.scp.spocr.base.servicefacades;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.century.scp.spocr.base.models.dto.DTO;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.models.domain.EventEnum;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class BaseServiceFacade<T extends DomainEntity, K extends DTO>
    implements ServiceFacade<T, K> {

  private final ServiceI<T> service;
  private final MapperI<T, K> mapper;
  private final EventRepositoryImpl eventRepository;

  @Override
  @Transactional(readOnly = true)
  public K get(Long id) {
    T entity = service.get(id);
    return mapper.map(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<K> get(Specification<T> specification, Pageable pageable) {
    Page<T> page = service.getBySpecification(specification, pageable);
    return mapper.map(page);
  }

  @Override
  @Transactional
  public K create(K request) {
    T entity = mapper.map(request);
    entity = service.create(entity);
    afterPersist(entity);
    return mapper.map(entity);
  }

  @Override
  @Transactional
  public K update(Long id, K request, List<String> properties) {
    T entity = mapper.map(request);
    entity = service.update(id, entity, properties);
    afterUpdate(entity, properties);
    return mapper.map(entity);
  }

  private void afterPersist(T entity) {
    log.debug("PostPersist body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    eventRepository.insert(
        EventEnum.CREATED.name(), entity.getClass().getName(), body, getCurrentUserLogin());
  }

  @PostUpdate
  private void afterUpdate(T entity, List<String> properties) {
    log.debug("PostUpdate body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    body.put("fields", properties);
    eventRepository.insert(
        EventEnum.UPDATED.name(), entity.getClass().getName(), body, getCurrentUserLogin());
  }

  private String getCurrentUserLogin() {
    return ((SecurityUser) getContext().getAuthentication().getPrincipal()).getLogin();
  }

}