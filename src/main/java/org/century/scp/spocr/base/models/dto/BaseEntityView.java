package org.century.scp.spocr.base.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseEntityView implements Serializable {

  private Long id;
  private Long version;
  @JsonIgnore
  public List<String> updatedFields;
  private Boolean active;

  protected BaseEntityView() {
    this.active = true;
  }

  public BaseEntityView(Long id, Long version, Boolean active) {
    this.id = id;
    this.version = version;
    this.active = active;
    this.updatedFields = Collections.emptyList();
  }
}
