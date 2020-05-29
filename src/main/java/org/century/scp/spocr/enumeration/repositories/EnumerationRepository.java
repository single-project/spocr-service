package org.century.scp.spocr.enumeration.repositories;

import java.util.Optional;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumerationRepository extends BaseRepository<Enumeration> {

  Optional<Enumeration> findByIdentAndValue(String ident, String value);
}
