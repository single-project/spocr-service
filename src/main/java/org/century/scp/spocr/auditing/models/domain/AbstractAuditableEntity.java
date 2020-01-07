package org.century.scp.spocr.auditing.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.century.scp.spocr.auditing.annotations.SaveTransientFieldsAfterMerge;
import org.century.scp.spocr.auditing.listeners.AuditableEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditableEntityListener.class)
public abstract class AbstractAuditableEntity {

  public abstract Long getId();

  @JsonIgnore
  @Transient
  @SaveTransientFieldsAfterMerge
  public List<String> updatedFields;

  @Version
  @Column
  private Long version;

}
