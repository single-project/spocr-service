package org.century.scp.spocr.base.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import java.io.IOException;
import java.util.List;

import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T extends BaseEntity> {

  public BaseRepository<T> entityRepository;

  public BaseService(BaseRepository<T> entityRepository) {
    this.entityRepository = entityRepository;
  }

  public abstract Class<T> getEntityClass();

  public abstract String getEntityName();

  public T create(T object) {
    return entityRepository.save(object);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public T update(Long id, String data) {
    T object = get(id);
    try {
      object = mergePatch(object, data, getEntityClass());
    } catch (IOException | JsonPatchException e) {
      throw new SpocrException(e);
    }
    return entityRepository.save(object);
  }

  public T get(long id) {
    return entityRepository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(id, getEntityName()));
  }

  public Page<T> getBySpecification(Specification<T> specification, Pageable pageable) {
    return entityRepository.findAll(specification, pageable);
  }

  public List<T> getAll() {
    return entityRepository.findAll();
  }

  public List<T> createAll(List<T> objects) {
    return entityRepository.saveAll(objects);
  }

  protected static <T> T mergePatch(T t, String patch, Class<T> clazz)
      throws IOException, JsonPatchException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    JsonNode node = mapper.convertValue(t, JsonNode.class);
    JsonNode patchNode = mapper.readTree(patch);
    JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
    node = mergePatch.apply(node);
    ArrayNode fields = ((ObjectNode) node).putArray("updatedFields");
    patchNode.fieldNames().forEachRemaining(fields::add);
    return mapper.treeToValue(node, clazz);
  }
}
