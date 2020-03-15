package org.century.scp.spocr.classifier.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@Data
@MappedSuperclass
@NoArgsConstructor
public class Classifier extends AbstractIdentifiedEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "manufactures_id", nullable = false)
  private Manufacturer manufacturer;

  @Column(name = "active")
  private Boolean active;

  public Classifier(String name, Manufacturer manufacturer, boolean active) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Classifier)) {
      return false;
    }
    return getId() != null && getId().equals(((Classifier) o).getId());
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
