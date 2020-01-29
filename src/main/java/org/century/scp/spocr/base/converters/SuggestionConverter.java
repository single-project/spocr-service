package org.century.scp.spocr.base.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import org.century.scp.spocr.exceptions.SpocrException;

public class SuggestionConverter implements AttributeConverter<LinkedHashMap, String> {

  @Override
  public String convertToDatabaseColumn(LinkedHashMap data) {
    try {
      return Objects.requireNonNullElse(new ObjectMapper().writeValueAsString(data), null);
    } catch (JsonProcessingException e) {
      throw new SpocrException("Ошибка преобразования данных", e);
    }
  }

  @Override
  public LinkedHashMap convertToEntityAttribute(String json) {
    try {
      return Objects.requireNonNullElse(
          new ObjectMapper().readValue(json, LinkedHashMap.class), null);
    } catch (IOException e) {
      throw new SpocrException("Ошибка преобразования данных", e);
    }
  }
}
