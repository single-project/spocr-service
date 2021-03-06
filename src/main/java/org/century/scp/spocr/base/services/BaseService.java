package org.century.scp.spocr.base.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.utils.CustomBeanUtils;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

@RequiredArgsConstructor
public abstract class BaseService<T extends IdentifiedEntity> implements ServiceI<T> {

  private final Class<T> entityClass;
  private final EntityManager entityManager;
  protected final BaseRepository<T> repository;

  @NonNull
  @Override
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public T get(long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(entityClass, id));
  }

  @NonNull
  @Override
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public Page<T> getPage(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @NonNull
  @Override
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public Page<T> getBySpecification(Specification<T> specification, Pageable pageable) {
    return repository.findAll(specification, pageable);
  }

  @NonNull
  @Override
  @PreAuthorize("hasAuthority('CREATE_PRIVILEGE')")
  public T create(T entity) {
    refresh(entity);
    return repository.save(entity);
  }

  @NonNull
  @Override
  @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
  public T update(Long id, T patch, List<String> properties) {
    if (!id.equals(patch.getId())) {
      throw new SpocrException("incorrect-entity-id.exception", id, patch.getId());
    }
    T current = get(id);
    CustomBeanUtils.copyProperties(patch, current, properties);
    refresh(current);
    return repository.save(current);
  }

  public abstract void refresh(T entity);

  public <K extends IdentifiedEntity> K getReference(K entity, Class<K> cl) {
    if (entity.getId() != null) {
      return entityManager.find(cl, entity.getId());
    } else {
      return entity;
    }
  }

  public <K extends IdentifiedEntity> Set<K> getReferences(Set<K> objects, Class<K> cl) {
    return objects.stream().map(e -> getReference(e, cl)).collect(Collectors.toSet());
  }

  public <K extends IdentifiedEntity> List<K> getReferences(List<K> objects, Class<K> cl) {
    return objects.stream().map(e -> getReference(e, cl)).collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    throw new UnsupportedOperationException("unsupported-operation-exception");
  }
}
