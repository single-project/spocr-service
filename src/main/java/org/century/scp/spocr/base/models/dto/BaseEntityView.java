package org.century.scp.spocr.base.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public abstract class BaseEntityView implements DTO {

  private Long id;
  private Long version;
  private Boolean active;

  protected BaseEntityView() {
    this.active = true;
  }

  public BaseEntityView(Boolean active) {
    this.active = active;
  }

  public BaseEntityView(Long version, Boolean active) {
    this.version = version;
    this.active = active;
  }

  public BaseEntityView(Long id, Long version, Boolean active) {
    this.id = id;
    this.version = version;
    this.active = active;
  }
}
