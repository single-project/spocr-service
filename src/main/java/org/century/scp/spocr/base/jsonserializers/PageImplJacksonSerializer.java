package org.century.scp.spocr.base.jsonserializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

@JsonComponent
public class PageImplJacksonSerializer<T> extends JsonSerializer<PageImpl<T>> {

  @Override
  public void serialize(PageImpl<T> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeObjectField("content", page.getContent());
    jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
    jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
    jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
    jsonGenerator.writeNumberField("size", page.getSize());
    jsonGenerator.writeNumberField("page", page.getNumber());
    jsonGenerator.writeFieldName("sort");
    jsonGenerator.writeStartObject();
    for (Sort.Order order : page.getSort()) {
      jsonGenerator.writeStringField("property", order.getProperty());
      jsonGenerator.writeStringField("direction", order.getDirection().name());
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.writeEndObject();
  }
}