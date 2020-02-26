package org.century.scp.spocr.enumeration.models.domain;

import java.util.LinkedHashMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.LinkedHashMapConverter;
import org.century.scp.spocr.base.models.domain.DomainEntity;

@Data
@Entity
@Table(name = "enumerations")
@NoArgsConstructor
public class Enumeration implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ident")
  private String ident;

  @Column(name = "value")
  private String value;

  @Column(name = "description_key")
  private String descriptionKey;

  @Column
  @Convert(converter = LinkedHashMapConverter.class)
  private LinkedHashMap properties;

}
