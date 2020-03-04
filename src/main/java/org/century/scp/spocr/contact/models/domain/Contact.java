package org.century.scp.spocr.contact.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.person.models.domain.Person;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contacts")
@NoArgsConstructor
public class Contact extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "contact_role_id", referencedColumnName = "id")
  private ContactRole role;

  @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
  @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  @Column(name = "comment")
  private String comment;

  @Column(name = "active")
  private Boolean active;

  public Contact(ContactRole role, Person person) {
    this.role = role;
    this.person = person;
    this.active = true;
  }
}
