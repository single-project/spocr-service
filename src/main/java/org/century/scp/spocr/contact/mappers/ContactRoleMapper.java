package org.century.scp.spocr.contact.mappers;

import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.century.scp.spocr.contact.models.dto.ContactRoleView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface ContactRoleMapper {

  ContactRole map(ContactRoleView view);

  ContactRoleView map(ContactRole entity);

  default Page<ContactRoleView> map(Page<ContactRole> page) {
    return page.map(this::map);
  }

}
