package org.century.scp.spocr.extregsystem.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;


@Entity
@Getter
@Setter
@Table(name = "ext_reg_systems")
@NoArgsConstructor
public class ExtRegSystem extends AbstractIdentifiedEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

}
