package org.century.scp.spocr.base.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page> {

  @Override
  public void serialize(
      Page page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeObjectField("content", page.getContent());
    jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
    jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
    jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
    jsonGenerator.writeNumberField("size", page.getSize());
    jsonGenerator.writeNumberField("page", page.getNumber());
    jsonGenerator.writeStringField("sort", page.getSort().toString());
    jsonGenerator.writeEndObject();
  }
}
