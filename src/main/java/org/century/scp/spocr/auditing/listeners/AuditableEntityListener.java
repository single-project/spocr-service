package org.century.scp.spocr.auditing.listeners;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.auditing.models.domain.EventEnum;
import org.century.scp.spocr.auditing.repositories.EventRepositoryImpl;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditableEntityListener {

  private EventRepositoryImpl eventRepository;

  @Autowired
  public AuditableEntityListener(EventRepositoryImpl eventRepository) {
    this.eventRepository = eventRepository;
  }

  @PostPersist
  private void afterPersist(BaseEntity entity) {
    log.debug("PostPersist body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    eventRepository.insert(EventEnum.CREATED.name(), entity.getClass().getName(), body);
  }

  @PostUpdate
  private void afterUpdate(BaseEntity entity) {
    log.debug("PostUpdate body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    body.put("fields", entity.getUpdatedFields());
    eventRepository.insert(EventEnum.UPDATED.name(), entity.getClass().getName(), body);
  }

  @PreRemove
  private void beforeRemove(Object object) {
    throw new SpocrException(
        "Операция удаления - недопустима", new UnsupportedOperationException());
  }
}
