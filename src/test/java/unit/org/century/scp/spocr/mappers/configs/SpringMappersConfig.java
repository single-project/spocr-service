package unit.org.century.scp.spocr.mappers.configs;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.legaltype.mappers.LegalTypeMapper;
import org.century.scp.spocr.legaltype.services.LegalTypeServiceImpl;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.paymentdetails.mappers.PaymentDetailsMapper;
import org.century.scp.spocr.shop.mappers.ShopMapper;
import org.century.scp.spocr.shoptype.mappers.ShopTypeMapper;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import unit.org.century.scp.spocr.mappers.factories.CounterpartyFactoryService;
import unit.org.century.scp.spocr.mappers.factories.ManufacturerFactoryService;
import unit.org.century.scp.spocr.mappers.factories.ShopFactoryService;

@Configuration
@ComponentScan(basePackageClasses = SpringMappersConfig.class)
public class SpringMappersConfig {

  @Bean
  public LegalTypeServiceImpl legalTypeService(
      CounterpartyFactoryService counterpartyFactoryService) {
    LegalTypeServiceImpl legalTypeService = Mockito.mock(LegalTypeServiceImpl.class);
    when(legalTypeService.get(anyLong()))
        .thenAnswer(
            (Answer<Counterparty>) invocation -> counterpartyFactoryService.createCounterparty(
                (long) invocation.getArguments()[0]));
    return legalTypeService;
  }

  @Bean
  public CounterpartyServiceImpl counterpartyService(
      CounterpartyFactoryService counterpartyFactoryService) {
    CounterpartyServiceImpl counterpartyService = Mockito.mock(CounterpartyServiceImpl.class);
    when(counterpartyService.get(anyLong()))
        .thenAnswer(
            (Answer<Counterparty>) invocation -> counterpartyFactoryService.createCounterparty(
                (long) invocation.getArguments()[0]));
    return counterpartyService;
  }

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

  @Bean
  public PaymentDetailsMapper paymentDetailsMapper() {
    return Mappers.getMapper(PaymentDetailsMapper.class);
  }

  @Bean
  public LegalTypeMapper legalTypeMapper() {
    return Mappers.getMapper(LegalTypeMapper.class);
  }

  @Bean
  public CounterpartyFactoryService counterpartyFactoryService() {
    return new CounterpartyFactoryService();
  }

  @Bean
  public ManufacturerFactoryService manufacturerFactoryService() {
    return new ManufacturerFactoryService();
  }

  @Bean
  public ShopFactoryService createShopFactoryService() {
    return new ShopFactoryService();
  }
}
