package org.century.scp.spocr.counterparties.repositories;

import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
  @Query(
      value =
          "SELECT * FROM {h-schema}counterparties"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  public Page<Counterparty> search(String query, Boolean active, Pageable pageable);
}
