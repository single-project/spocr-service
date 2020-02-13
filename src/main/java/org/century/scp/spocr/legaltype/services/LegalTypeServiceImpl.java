package org.century.scp.spocr.legaltype.services;

import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.repository.LegalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalTypeServiceImpl extends BaseService<LegalType> {

  @Autowired
  public LegalTypeServiceImpl(LegalTypeRepository entityRepository) {
    super(entityRepository);
  }

  @Override
  public Class<LegalType> getEntityClass() {
    return LegalType.class;
  }

  @Override
  public String getEntityName() {
    return "организационно-правоая форма";
  }

}
