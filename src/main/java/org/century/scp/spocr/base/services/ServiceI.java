package org.century.scp.spocr.base.services;

import java.util.List;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ServiceI<T extends IdentifiedEntity> {

  @NonNull
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  T get(long id);

  @NonNull
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  Page<T> getBySpecification(Specification<T> specification, Pageable pageable);

  @NonNull
  @PreAuthorize("hasAuthority('CREATE_PRIVILEGE')")
  T create(T entity);

  @NonNull
  @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
  T update(Long id, T entity, List<String> properties);

}
