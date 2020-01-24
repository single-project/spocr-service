package org.century.scp.spocr.base.models.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityView implements Serializable {

  private Long id;
  private String name;
  private Long version;
  private boolean active;

  public BaseEntityView(Long id) {
    this.id = id;
  }

  public BaseEntityView(String name) {
    this.name = name;
    this.active = true;
  }
}
