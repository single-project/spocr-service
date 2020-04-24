package integration.generators.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.contract.models.dto.ContractView;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.extregsystem.models.dto.ExtRegSystemView;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.junit.Test;

public class Generate {

  @Test
  public void generateExtRegSystem() {
    generateJsonSchema(ExtRegSystemView.class);
  }

  @Test
  public void generateContract() {
    generateJsonSchema(ContractView.class);
  }

  @Test
  public void generateManufacturer() {
    generateJsonSchema(ManufacturerView.class);
  }

  @Test
  public void generateClassifier() {
    generateJsonSchema(ClassifierView.class);
  }

  @Test
  public void generateOwner() {
    generateJsonSchema(Owner.class);
  }

  @Test
  public void generateShop() {
    generateJsonSchema(ShopView.class);
  }

  @Test
  public void generateCounterparty() {
    generateJsonSchema(CounterpartyView.class);
  }

  private void generateJsonSchema(Class c) {
    ObjectMapper objectMapper = new ObjectMapper();
    SchemaGeneratorConfigBuilder configBuilder =
        new SchemaGeneratorConfigBuilder(
            objectMapper, SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
    SchemaGeneratorConfig config = configBuilder.build();
    SchemaGenerator generator = new SchemaGenerator(config);
    JsonNode jsonSchema = generator.generateSchema(c);
    System.out.println(String.format("%s %s", c.getSimpleName(), jsonSchema.toString()));
  }
}
