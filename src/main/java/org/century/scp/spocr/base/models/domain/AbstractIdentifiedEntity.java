package org.century.scp.spocr.base.models.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractIdentifiedEntity implements IdentifiedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Version
  @Column(name = "VERSION")
  private Long version;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdentifiedEntity)) {
      return false;
    }
    return getId() != null && getId().equals(((IdentifiedEntity) o).getId());
  }

  @Override
  public int hashCode() {
    return 31;
  }

}
