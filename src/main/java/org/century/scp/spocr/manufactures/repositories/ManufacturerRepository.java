package org.century.scp.spocr.manufactures.repositories;

import org.century.scp.spocr.manufactures.models.domain.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

  @Query(
      value =
          "SELECT * FROM {h-schema}manufactures"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  Page<Manufacturer> search(String query, Boolean active, Pageable pageable);
}
