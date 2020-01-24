package org.century.scp.spocr.base.models.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.auditing.annotations.SaveTransientFieldsAfterMerge;
import org.century.scp.spocr.auditing.listeners.AuditableEntityListener;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditableEntityListener.class)
@NoArgsConstructor
public abstract class BaseEntity<K extends BaseEntityView>
    implements NamedEntity, VersionableEntity, PartialUpdatableEntity {

  @Transient @SaveTransientFieldsAfterMerge public List<String> updatedFields;

  @Version
  @Column(columnDefinition = "long not null default 0")
  private Long version;

  public BaseEntity(Long version) {
    this.version = version;
  }

  public abstract Long getId();

  public abstract String getName();

  public abstract void setName(String name);

  public abstract Boolean getActive();

}
