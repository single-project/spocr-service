package org.century.scp.spocr.shops.repositories;

import org.century.scp.spocr.shops.models.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<Shop, Long> {

  @Query(
      value =
          "SELECT s FROM {h-schema}shops"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  Page<Shop> search(String query, Boolean active, Pageable pageable);
}
