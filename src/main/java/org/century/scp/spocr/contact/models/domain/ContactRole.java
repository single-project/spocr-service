package org.century.scp.spocr.contact.models.domain;

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
@Table(name = "contact_roles")
@NoArgsConstructor
public class ContactRole extends AbstractIdentifiedEntity {

  @Column(name = "name")
  private String name;

  public ContactRole(String name) {
    this.name = name;
  }
}
