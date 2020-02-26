package org.century.scp.spocr.base.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.persistence.AttributeConverter;
import org.century.scp.spocr.exceptions.SpocrException;

public class LinkedHashMapConverter implements AttributeConverter<LinkedHashMap, String> {

  @Override
  public String convertToDatabaseColumn(LinkedHashMap data) {
    try {
      return data != null ? new ObjectMapper().writeValueAsString(data) : null;
    } catch (JsonProcessingException e) {
      throw new SpocrException("Ошибка преобразования данных", e);
    }
  }

  @Override
  public LinkedHashMap convertToEntityAttribute(String json) {
    try {
      return json != null ? new ObjectMapper().readValue(json, LinkedHashMap.class) : null;
    } catch (IOException e) {
      throw new SpocrException("Ошибка преобразования данных", e);
    }
  }
}
