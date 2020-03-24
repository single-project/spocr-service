package org.century.scp.spocr.address.models.domain;

import java.util.LinkedHashMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.MapConverter;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address")
  private String address;

  @Column
  private Double latitude;

  @Column
  private Double longitude;

  @Column(name = "comment")
  private String comment;

  @Column(name = "suggestion")
  @Convert(converter = MapConverter.class)
  private LinkedHashMap suggestion;

  @Version
  @Column(name = "VERSION")
  private Long version;

  public Address(String address) {
    this.address = address;
  }

  public Address(
      Long id, String address, String comment, LinkedHashMap suggestion, Boolean active) {
    this.id = id;
    this.address = address;
    this.comment = comment;
    this.suggestion = suggestion;
  }
}
