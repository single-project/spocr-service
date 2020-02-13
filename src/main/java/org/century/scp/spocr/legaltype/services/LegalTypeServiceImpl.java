package org.century.scp.spocr.legaltype.services;

import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.repository.LegalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalTypeServiceImpl extends BaseService<LegalType> {

  @Autowired
  public LegalTypeServiceImpl(DefaultMessageSource messageSource,
      LegalTypeRepository entityRepository) {
    super(messageSource, entityRepository);
  }

  @Override
  public Class<LegalType> getEntityClass() {
    return LegalType.class;
  }

}
