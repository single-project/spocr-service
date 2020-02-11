package org.century.scp.spocr.address.mappers;

import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface AddressMapper {

  AddressView map(Address entity);

  Address map(AddressView view);

  default Page<AddressView> map(Page<Address> page) {
    return page.map(this::map);
  }
}
