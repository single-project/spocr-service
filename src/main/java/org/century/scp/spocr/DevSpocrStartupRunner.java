package org.century.scp.spocr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.services.ShopServiceImpl;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
public class DevSpocrStartupRunner implements ApplicationRunner {

  @Autowired
  private CounterpartyServiceImpl counterpartyService;
  @Autowired
  private ManufacturerServiceImpl manufacturerService;
  @Autowired
  private ShopTypesServiceImpl shopTypesService;
  @Autowired
  private ShopServiceImpl shopService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // add 10 new counteragent
    List<Counterparty> counterparties = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      counterparties.add(new Counterparty("Контагент" + i));
    }
    counterpartyService.createAll(counterparties);

    // add 2 new manufacturer
    Manufacturer m1 = manufacturerService.create(new Manufacturer("ООО Производитель1"));
    Manufacturer m2 = manufacturerService.create(new Manufacturer("ООО Производитель2"));

    // add 5 new shop types
    shopTypesService.create(new ShopType("Супермаркет", m1));
    shopTypesService.create(new ShopType("Маркет", m1));
    shopTypesService.create(new ShopType("Магазин", m1));
    shopTypesService.create(new ShopType("Ларек", m2));
    shopTypesService.create(new ShopType("Прилавок", m2));

    // add 1000 new shops
    List<Shop> shops = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) {
      shops.add(
          new Shop(
              "Магазин" + i,
              counterpartyService.get(new Random().nextInt(9) + 1),
              shopTypesService.get(new Random().nextInt(5) + 1)));
    }
    shopService.createAll(shops);
  }
}
