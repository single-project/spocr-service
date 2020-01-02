package org.century.scp.spocr.events.listeners;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.events.models.domain.AbstractAuditableEntity;
import org.century.scp.spocr.events.repositories.EventRepositoryImpl;
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
  private void afterPersist(AbstractAuditableEntity entity) {
    log.debug("PostPersist body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    eventRepository.insert("create", body);
  }

  @PostUpdate
  private void afterUpdate(AbstractAuditableEntity entity) {
    log.debug("PostUpdate body {}", entity.toString());
    Map<String, Object> body = new HashMap<>();
    body.put("id", entity.getId());
    body.put("fields", entity.getUpdatedFields());
    eventRepository.insert("update", body);
  }

  @PreRemove
  private void beforeRemove(Object object) {
    throw new SpocrException(
        "Операция удаления - недопустима", new UnsupportedOperationException());
  }
}
