package org.century.scp.spocr.person.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.person.models.domain.Person;
import org.century.scp.spocr.person.models.dto.PersonView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonMapperFacade implements MapperI<Person, PersonView> {

  private final PersonMapper personMapper;

  @Override
  public Person map(PersonView dto) {
    return personMapper.map(dto);
  }

  @Override
  public PersonView map(Person domain) {
    return personMapper.map(domain);
  }

  @Override
  public Page<PersonView> map(Page<Person> page) {
    return personMapper.map(page);
  }
}
