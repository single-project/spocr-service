package org.century.scp.spocr.base.models.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntityView implements Serializable {

  private Long id;
  private Long version;
  private Boolean active;

  protected BaseEntityView() {
    this.active = true;
  }

  public BaseEntityView(Long id, Long version, Boolean active) {
    this.id = id;
    this.version = version;
    this.active = active;
  }
}
