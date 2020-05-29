package org.century.scp.spocr.contract.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.models.dto.SubContractView;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubContractServiceFacade extends BaseServiceFacade<SubContract, SubContractView> {

  public SubContractServiceFacade(
      ServiceI<SubContract> service,
      MapperI<SubContract, SubContractView> mapper,
      EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
