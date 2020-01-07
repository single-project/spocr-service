package org.century.scp.spocr.shoptype.repositories;

import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopTypeRepository extends BaseRepository<ShopType> {

  @Query(
      value =
          "SELECT * FROM {h-schema}shop_types"
              + " WHERE "
              + "(:query is null or name like %:query%) "
              + "and (:active is null or active=:active)",
      nativeQuery = true)
  Page<ShopType> search(String query, Boolean active, Pageable pageable);

}
