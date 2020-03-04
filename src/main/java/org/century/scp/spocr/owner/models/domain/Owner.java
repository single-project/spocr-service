package org.century.scp.spocr.owner.models.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.contact.models.domain.Contact;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "owners")
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "owner_contacts",
      joinColumns = @JoinColumn(name = "owner_id"),
      inverseJoinColumns = @JoinColumn(name = "contacts_id"))
  private List<Contact> contacts;

  public boolean linkedWithContacts() {
    return contacts != null && contacts.size() > 0;
  }

  public void addContact(Contact contact) {
    if (contacts == null) {
      this.contacts = new ArrayList<>();
    }
    contacts.add(contact);
  }
}
