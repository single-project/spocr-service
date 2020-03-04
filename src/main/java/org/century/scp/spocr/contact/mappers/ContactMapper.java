package org.century.scp.spocr.contact.mappers;

import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.century.scp.spocr.person.mappers.PersonMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(uses = {ContactRoleMapper.class, PersonMapper.class})
public interface ContactMapper {

  Contact map(ContactView view);

  ContactView map(Contact entity);

  default Page<ContactView> map(Page<Contact> page) {
    return page.map(this::map);
  }
}
