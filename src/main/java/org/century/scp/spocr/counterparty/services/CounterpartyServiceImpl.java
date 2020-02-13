package org.century.scp.spocr.counterparty.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.repositories.CounterpartyRepository;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.services.LegalTypeServiceImpl;
import org.century.scp.spocr.paymentdetails.services.PaymentDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyServiceImpl extends BaseService<Counterparty> {

  private LegalTypeServiceImpl legalTypeService;
  private PaymentDetailsServiceImpl paymentDetailsService;

  @Autowired
  public CounterpartyServiceImpl(
      LegalTypeServiceImpl legalTypeService,
      PaymentDetailsServiceImpl paymentDetailsService,
      CounterpartyRepository counterpartyRepository) {
    super(counterpartyRepository);
    this.legalTypeService = legalTypeService;
    this.paymentDetailsService = paymentDetailsService;
  }

  @Override
  public Class<Counterparty> getEntityClass() {
    return Counterparty.class;
  }

  @Override
  public String getEntityName() {
    return "контрагент";
  }

  @Override
  public Counterparty assemble(Counterparty entity) {
    // parent
    if (entity.getParent() != null) {
      entity.setParent(get(entity.getParent().getId()));
    }

    // legaltype
    LegalType legalType = entity.getLegalType();
    if (legalType != null) {
      entity.setLegalType(legalTypeService.createOrRefresh(legalType));
    }

    return entity;
  }
}
