package org.century.scp.spocr.person.repositories;

import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.person.models.domain.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person> {

}
