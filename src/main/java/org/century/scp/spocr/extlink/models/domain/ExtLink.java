package org.century.scp.spocr.extlink.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.extlink.models.EntityType;

@Data
@Entity
@Table(name = "external_ids")
@NoArgsConstructor
public class ExtLink {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "entity_id")
  private int entityId;

  @Column(name = "entity_ext_id")
  private int entityExtId;

  @Enumerated(EnumType.STRING)
  @Column(name = "entity_type")
  private EntityType entityType;

  @Column(name = "ext_prog_id")
  private int extProgId;
}
