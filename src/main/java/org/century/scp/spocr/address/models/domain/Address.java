package org.century.scp.spocr.address.models.domain;

import java.util.LinkedHashMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.SuggestionConverter;
import org.century.scp.spocr.base.models.domain.BaseEntity;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
public class Address extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address")
  private String address;

  @Column(name = "comment")
  private String comment;

  @Column(name = "suggestion")
  @Convert(converter = SuggestionConverter.class)
  private LinkedHashMap suggestion;

  @Column(name = "active")
  private Boolean active;

  public Address(String address) {
    this.address = address;
    this.active = true;
  }

  public Address(Long id, String address, String comment, LinkedHashMap suggestion,
      Boolean active) {
    this.id = id;
    this.address = address;
    this.comment = comment;
    this.suggestion = suggestion;
    this.active = active;
  }
}
