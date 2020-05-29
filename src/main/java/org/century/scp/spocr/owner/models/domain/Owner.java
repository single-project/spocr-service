package org.century.scp.spocr.owner.models.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.domain.AbstractIdentifiedEntity;
import org.century.scp.spocr.contact.models.domain.Contact;

@Entity
@Getter
@Setter
@Table(name = "owners")
@NoArgsConstructor
public class Owner extends AbstractIdentifiedEntity {

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
