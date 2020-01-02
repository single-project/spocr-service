package org.century.scp.spocr.events.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import java.io.IOException;

public class AuditableEntityServiceImpl {

  public static <T> T mergePatch(T t, String patch, Class<T> clazz)
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
