package org.century.scp.spocr.legaltype.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;

@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "legal_types")
@NoArgsConstructor
@AllArgsConstructor
public class LegalType extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "okpf_short_name")
  private String okfpShortName;

  @Column(name = "okpf_full_name")
  private String okfpFullName;

  @Column(name = "okpf_id")
  private String okpfId;

  @Column(name = "okpf_type")
  private String okpfType;

  @Column(name = "active")
  private Boolean active;

  public LegalType(Long id) {
    setId(id);
  }
}
