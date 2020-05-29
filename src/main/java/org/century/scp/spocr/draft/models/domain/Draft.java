package org.century.scp.spocr.draft.models.domain;

import java.time.Instant;
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
import org.century.scp.spocr.base.converters.MapConverter;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;

@Data
@Entity
@Table(name = "drafts")
@NoArgsConstructor
public class Draft implements IdentifiedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "entity")
  private String entity;

  @Column(name = "username")
  private String username;

  @Column(name = "ts")
  private Instant ts;

  @Column
  @Convert(converter = MapConverter.class)
  private LinkedHashMap body;


}
