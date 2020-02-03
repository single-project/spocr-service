package unit.org.century.scp.spocr.mappers;

import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.shop.mappers.ShopMapper;
import org.century.scp.spocr.shoptype.mappers.ShopTypeMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringMappersConfig.class)
public class SpringMappersConfig {

  @Bean
  public AddressMapper addressMapper() {
    return Mappers.getMapper(AddressMapper.class);
  }

  @Bean
  public ManufacturerMapper manufacturerMapper() {
    return Mappers.getMapper(ManufacturerMapper.class);
  }

  @Bean
  public CounterpartyMapper counterpartyMapper() {
    return Mappers.getMapper(CounterpartyMapper.class);
  }

  @Bean
  public ShopTypeMapper shopTypeMapper() {
    return Mappers.getMapper(ShopTypeMapper.class);
  }

  @Bean
  public ShopMapper shopMapper() {
    return Mappers.getMapper(ShopMapper.class);
  }
}
