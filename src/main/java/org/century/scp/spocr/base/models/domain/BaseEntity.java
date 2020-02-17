package org.century.scp.spocr.base.models.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements VersionableEntity, DomainEntity {

  @Column
  @Version
  private Long version;

  public BaseEntity(Long version) {
    this.version = version;
  }

  public abstract Long getId();

  public abstract Boolean getActive();
}
