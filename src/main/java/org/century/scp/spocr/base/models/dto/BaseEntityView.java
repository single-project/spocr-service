package org.century.scp.spocr.base.models.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntityView implements Serializable {
  private long id;
  private String name;
  private long version;
  private boolean active;

  public BaseEntityView(BaseEntity entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.version = entity.getVersion();
    this.active = entity.getActive();
  }

  public BaseEntityView(long id, String name, long version, boolean active) {
    this.id = id;
    this.name = name;
    this.version = version;
    this.active = active;
  }
}
