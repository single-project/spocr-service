package org.century.scp.spocr.base.models.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseEntityView implements Serializable {

  private Long id;
  private Long version;
  private boolean active;

  public BaseEntityView(Long id) {
    this.id = id;
  }

  public BaseEntityView() {
    this.active = true;
  }
}
