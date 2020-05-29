package org.century.scp.spocr.contact.mappers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactMapperFacade implements MapperI<Contact, ContactView> {

  private final ContactMapper contactMapper;

  @Override
  public Contact map(ContactView dto) {
    return contactMapper.map(dto);
  }

  @Override
  public ContactView map(Contact domain) {
    return contactMapper.map(domain);
  }

  @Override
  public Page<ContactView> map(Page<Contact> page) {
    return contactMapper.map(page);
  }
}
