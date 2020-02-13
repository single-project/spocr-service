package org.century.scp.spocr.base.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.google.common.base.CaseFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T extends BaseEntity> {

  protected BaseRepository<T> entityRepository;
  private DefaultMessageSource messageSource;

  public BaseService(DefaultMessageSource messageSource, BaseRepository<T> entityRepository) {
    this.messageSource = messageSource;
    this.entityRepository = entityRepository;
  }

  @PreAuthorize("hasAuthority('CREATE_PRIVILEGE')")
  public T create(T object) {
    T assembly = assemble(object);
    return entityRepository.save(assembly);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
  public T update(Long id, T patch) {
    T current = get(id);
    try {
      current = mergePatch(current, patch, getEntityClass());
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    T assembly = assemble(current);
    return entityRepository.save(assembly);
  }

  @NonNull
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public T get(long id) {
    return entityRepository
        .findById(id)
        .orElseThrow(
            () ->
                new SpocrEntityNotFoundException(
                    id, messageSource.getMessage(getResourceNameKey())));
  }

  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public Page<T> getBySpecification(Specification<T> specification, Pageable pageable) {
    return entityRepository.findAll(specification, pageable);
  }

  @PreAuthorize("hasAuthority('CREATE_PRIVILEGE')")
  public List<T> createAll(List<T> objects) {
    return entityRepository.saveAll(objects);
  }

  @NonNull
  @PreAuthorize("hasAuthority('CREATE_PRIVILEGE')")
  public T createOrRefresh(@NonNull T object) {
    if (object.getId() == null) {
      return create(object);
    } else {
      return get(object.getId());
    }
  }

  protected T mergePatch(T currentEntity, T patchEntity, Class<T> clazz)
      throws IOException, JsonPatchException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    JsonNode node = mapper.convertValue(currentEntity, JsonNode.class);
    JsonNode patchNode = mapper.convertValue(patchEntity, JsonNode.class);
    List<String> fields = new ArrayList<>();
    patchNode.fieldNames().forEachRemaining(fields::add);
    List<String> updatedFields = patchEntity.getUpdatedFields();
    fields.stream()
        .filter(f -> !updatedFields.contains(f))
        .forEach(((ObjectNode) patchNode)::remove);
    JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
    node = mergePatch.apply(node);
    return mapper.treeToValue(node, clazz);
  }

  public abstract Class<T> getEntityClass();

  private String getResourceNameKey() {
    String name = getEntityClass().getSimpleName();
    return String.format("%s.resource.name",
        CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(name));
  }

  public T assemble(T entity) {
    return entity;
  }
}
