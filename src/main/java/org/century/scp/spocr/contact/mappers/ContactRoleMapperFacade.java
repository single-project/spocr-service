package org.century.scp.spocr.contact.mappers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.century.scp.spocr.contact.models.dto.ContactRoleView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactRoleMapperFacade implements MapperI<ContactRole, ContactRoleView> {

  private final ContactRoleMapper contactRoleMapper;

  @Override
  public ContactRole map(ContactRoleView dto) {
    return contactRoleMapper.map(dto);
  }

  @Override
  public ContactRoleView map(ContactRole domain) {
    return contactRoleMapper.map(domain);
  }

  @Override
  public Page<ContactRoleView> map(Page<ContactRole> page) {
    return contactRoleMapper.map(page);
  }
}
