package org.century.scp.spocr.counterparties.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import java.io.IOException;

public class JsonMergePatchUtils {
  public static <T> T mergePatch(T t, String patch, Class<T> clazz)
      throws IOException, JsonPatchException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    JsonNode node = mapper.convertValue(t, JsonNode.class);
    JsonNode patchNode = mapper.readTree(patch);
    JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
    node = mergePatch.apply(node);
    return mapper.treeToValue(node, clazz);
  }
}
