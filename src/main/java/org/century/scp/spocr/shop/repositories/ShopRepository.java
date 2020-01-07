package org.century.scp.spocr.shop.repositories;

import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends BaseRepository<Shop> {

  @Query(
      value =
          "SELECT * FROM {h-schema}shops"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  Page<Shop> search(String query, Boolean active, Pageable pageable);
}
