package org.century.scp.spocr.extlink.services;

import java.util.List;
import org.century.scp.spocr.exceptions.SpocrConstraintViolationException;
import org.century.scp.spocr.extlink.models.domain.ExtLink;
import org.century.scp.spocr.extlink.repositories.ExtLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExtLinkServiceImpl {
  private ExtLinkRepository extLinkRepository;

  @Autowired
  public ExtLinkServiceImpl(
      ExtLinkRepository extLinkRepository) {
    this.extLinkRepository = extLinkRepository;
  }

  @Transactional
  public ExtLink create(ExtLink extLink) {
    try {
      return extLinkRepository.save(extLink);
    } catch (DataIntegrityViolationException e) {
      throw new SpocrConstraintViolationException("Подобная связь уже существует");
    }
  }

  public List<ExtLink> getBySpecification(Specification<ExtLink> specification) {
    return extLinkRepository.findAll(specification);
  }
}
