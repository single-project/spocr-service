package org.century.scp.spocr.person.mappers;

import java.util.Objects;
import org.century.scp.spocr.enumeration.mappers.EnumerationMapper;
import org.century.scp.spocr.person.models.domain.Person;
import org.century.scp.spocr.person.models.dto.PersonView;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(uses = EnumerationMapper.class)
public abstract class PersonMapper {

  @Mapping(target = "name", ignore = true)
  public abstract PersonView map(Person entity);

  @InheritInverseConfiguration
  public abstract Person map(PersonView view);

  public Page<PersonView> map(Page<Person> page) {
    return page.map(this::map);
  }

  @AfterMapping
  public void fillName(Person entity, @MappingTarget PersonView view) {
    String lastName = entity.getLastName();
    String firstName = entity.getFirstName();
    String patronymic = entity.getPatronymic();
    view.setName(
        String.format(
            "%s %s %s",
            Objects.toString(lastName, ""),
            Objects.toString(firstName, ""),
            Objects.toString(patronymic, "")));
  }
}
