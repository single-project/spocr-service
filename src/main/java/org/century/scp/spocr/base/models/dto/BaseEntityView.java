package org.century.scp.spocr.base.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntityView implements DTO {

  private Long id;
  private Long version;
  private Boolean active;

  protected BaseEntityView() {
    this.active = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseEntityView)) {
      return false;
    }
    return getId() != null && getId().equals(((BaseEntityView) o).getId());
  }

  @Override
  public int hashCode() {
    return 31;
  }

}
