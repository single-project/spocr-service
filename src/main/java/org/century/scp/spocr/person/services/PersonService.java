package org.century.scp.spocr.person.services;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.person.models.domain.Person;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService extends BaseService<Person> {

  public PersonService(EntityManager entityManager, BaseRepository<Person> repository) {
    super(Person.class, entityManager, repository);
  }

  public void refresh(Person person) {
    if (person.getDocType() != null) {
      person.setDocType(getReference(person.getDocType(), Enumeration.class));
    }

    if (person.getCitizenship() != null) {
      person.setCitizenship(getReference(person.getCitizenship(), Enumeration.class));
    }

    if (person.getGender() != null) {
      person.setGender(getReference(person.getGender(), Enumeration.class));
    }
  }
}
