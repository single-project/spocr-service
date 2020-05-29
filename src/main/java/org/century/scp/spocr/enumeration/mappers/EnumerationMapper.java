package org.century.scp.spocr.enumeration.mappers;

import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper
public abstract class EnumerationMapper {

  @Autowired
  private DefaultMessageSource messageSource;

  @Mapping(target = "ident", ignore = true)
  @Mapping(target = "value", ignore = true)
  @Mapping(target = "descriptionKey", ignore = true)
  public abstract Enumeration map(EnumerationView view);

  @Mapping(target = "name", ignore = true)
  public abstract EnumerationView map(Enumeration entity);

  public Page<EnumerationView> map(Page<Enumeration> page) {
    return page.map(this::map);
  }

  @AfterMapping
  void fillValueByKey(Enumeration entity, @MappingTarget EnumerationView view) {
    view.setName(messageSource.getMessage(entity.getDescriptionKey()));
  }

}
