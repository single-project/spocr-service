package unit.org.century.scp.spocr.mappers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import unit.org.century.scp.spocr.mappers.AddressMapperTest.SpringTestConfig;

@ContextConfiguration(classes = SpringTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressMapperTest {

  @Autowired
  private AddressMapper addressMapper;

  @Test
  public void correctMapAddressToView() {
    String adr = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    Address address = new Address(adr);
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put(1, "s" + 1);
    suggestion.put("s" + 1, 1);
    address.setSuggestion(suggestion);
    AddressView addressView = addressMapper.map(address);

    assertTrue(addressView.isActive());
    assertEquals(adr, addressView.getAddress());
    assertThat(addressView.getSuggestion().size(), is(2));
    assertThat(addressView.getSuggestion().get(1), is("s1"));
  }

  @Test
  public void correctMapViewToAddress() {
    String adr = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put(1, "s" + 1);
    suggestion.put("s" + 1, 1);
    AddressView addressView = new AddressView(adr);
    long i = 194;
    addressView.setId(i);
    addressView.setSuggestion(suggestion);

    Address address = addressMapper.map(addressView);
    assertThat(address.getId(), is(i));
    assertEquals(adr, address.getAddress());
    assertThat(address.getSuggestion().size(), is(2));
    assertThat(address.getSuggestion().get(1), is("s1"));

  }

  @Configuration
  @ComponentScan(basePackageClasses = AddressMapperTest.class)
  public static class SpringTestConfig {

    @Bean
    public AddressMapper addressMapper() {
      return Mappers.getMapper(AddressMapper.class);
    }
  }
}
