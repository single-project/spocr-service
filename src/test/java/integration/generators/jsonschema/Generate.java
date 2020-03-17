package integration.generators.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.junit.jupiter.api.Test;

public class Generate {

  @Test
  public void generateCounterparty() {
    ObjectMapper objectMapper = new ObjectMapper();
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,
        SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(CounterpartyView.class);
    System.out.println(jsonSchema.toString());
  }

}
