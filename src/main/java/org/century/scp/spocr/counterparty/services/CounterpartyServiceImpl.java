package org.century.scp.spocr.counterparty.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.repositories.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyServiceImpl extends BaseService<Counterparty> {

  @Autowired
  public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
    super(counterpartyRepository);
  }

  @Override
  public Class<Counterparty> getEntityClass() {
    return Counterparty.class;
  }

  @Override
  public String getEntityName() {
    return "контрагент";
  }
}
