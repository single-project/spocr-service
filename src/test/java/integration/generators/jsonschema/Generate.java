package integration.generators.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.junit.jupiter.api.Test;

public class Generate {

  @Test
  public void generateOwner() {
    ObjectMapper objectMapper = new ObjectMapper();
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,
        SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(OwnerView.class);
    System.out.println("owner " + jsonSchema.toString());
  }

  @Test
  public void generateShop() {
    ObjectMapper objectMapper = new ObjectMapper();
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,
        SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(ShopView.class);
    System.out.println("shop " + jsonSchema.toString());
  }

  @Test
  public void generateCounterparty() {
    ObjectMapper objectMapper = new ObjectMapper();
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,
        SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(CounterpartyView.class);
    System.out.println("counterparty" + jsonSchema.toString());
  }

}
