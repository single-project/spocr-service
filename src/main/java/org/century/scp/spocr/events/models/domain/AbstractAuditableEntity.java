package org.century.scp.spocr.events.models.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.century.scp.spocr.events.annotations.SaveTransientFieldsAfterMerge;
import org.century.scp.spocr.events.listeners.AuditableEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditableEntityListener.class)
public abstract class AbstractAuditableEntity {

  public abstract Long getId();

  @Transient
  @SaveTransientFieldsAfterMerge
  public List<String> updatedFields;

  @Version
  @Column
  private Long version;

}
