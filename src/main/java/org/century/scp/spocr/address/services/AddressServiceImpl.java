package org.century.scp.spocr.address.services;

import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.respositories.AddressRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseService<Address> {

  public AddressServiceImpl(AddressRepository addressRepository) {
    super(addressRepository);
  }

  @Override
  public Class<Address> getEntityClass() {
    return Address.class;
  }

  @Override
  public String getEntityName() {
    return "адрес";
  }
}
