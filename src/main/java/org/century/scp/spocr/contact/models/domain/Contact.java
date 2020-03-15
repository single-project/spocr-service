package org.century.scp.spocr.contact.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.person.models.domain.Person;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Getter
@Setter
@Table(name = "contacts")
@NoArgsConstructor
public class Contact extends AbstractIdentifiedEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "contact_role_id", referencedColumnName = "id")
  private ContactRole role;

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  @Column(name = "comment")
  private String comment;

  public Contact(ContactRole role, Person person) {
    this.role = role;
    this.person = person;
  }
}
