package org.century.scp.spocr.shoptypes.repositories;

import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopTypeRepository extends JpaRepository<ShopType, Long> {

  @Query(
      value =
          "SELECT * FROM {h-schema}shop_types"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  Page<ShopType> search(String query, Boolean active, Pageable pageable);

}
