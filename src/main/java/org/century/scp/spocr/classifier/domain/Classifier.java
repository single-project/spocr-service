package org.century.scp.spocr.classifier.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;

@Data
@MappedSuperclass
@NoArgsConstructor
public class Classifier extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "manufactures_id", nullable = false)
  private Manufacturer manufacturer;

  @Column(name = "active")
  private Boolean active;

  public Classifier(Long id, String name, boolean active, long version, Manufacturer manufacturer) {
    super(version);
    this.id = id;
    this.name = name;
    this.active = active;
    this.manufacturer = manufacturer;
  }

  public Classifier(String name, Manufacturer manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.active = true;
  }

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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Classifier that = (Classifier) o;
    try {
      return getId().equals(that.getId());
    } catch (Throwable e) {
      throw new NullPointerException();
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getId());
  }

  @Override
  public String toString() {
    return "Classifier{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", manufacturer=" + manufacturer.getName() +
        ", active=" + active +
        '}';
  }
}
