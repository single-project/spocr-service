package org.century.scp.spocr.base.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.SpocrException;

@Slf4j
public class MapConverter implements AttributeConverter<Map, String> {

  @Override
  public String convertToDatabaseColumn(Map data) {
    try {
      return data != null ? new ObjectMapper().writeValueAsString(data) : null;
    } catch (JsonProcessingException e) {
      log.error("failed-to-convert-data.exception", e);
      throw new SpocrException("failed-to-convert-data.exception");
    }
  }

  @Override
  public Map convertToEntityAttribute(String json) {
    try {
      return json != null ? new ObjectMapper().readValue(json, Map.class) : null;
    } catch (IOException e) {
      log.error("failed-to-convert-data.exception", e);
      throw new SpocrException("failed-to-convert-data.exception");
    }
  }
}
