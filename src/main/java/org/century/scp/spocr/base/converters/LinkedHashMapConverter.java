package org.century.scp.spocr.base.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.SpocrException;

@Slf4j
public class LinkedHashMapConverter implements AttributeConverter<LinkedHashMap, String> {

  @Override
  public String convertToDatabaseColumn(LinkedHashMap data) {
    try {
      return data != null ? new ObjectMapper().writeValueAsString(data) : null;
    } catch (JsonProcessingException e) {
      log.error("failed-to-convert-data.exception", e);
      throw new SpocrException("failed-to-convert-data.exception");
    }
  }

  @Override
  public LinkedHashMap convertToEntityAttribute(String json) {
    try {
      return json != null ? new ObjectMapper().readValue(json, LinkedHashMap.class) : null;
    } catch (IOException e) {
      log.error("failed-to-convert-data.exception", e);
      throw new SpocrException("failed-to-convert-data.exception");
    }
  }
}
