package org.century.scp.spocr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.services.CounterpartyServiceImpl;
import org.century.scp.spocr.manufactures.models.domain.Manufacturer;
import org.century.scp.spocr.manufactures.services.ManufacturerServiceImpl;
import org.century.scp.spocr.shops.models.domain.Shop;
import org.century.scp.spocr.shops.services.ShopServiceImpl;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.services.ShopTypesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
    List<Counterparty> items = new ArrayList<>();
    Resource resource = loadItems();

    InputStream stream = resource.getInputStream();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

      String line;
      while ((line = reader.readLine()) != null) {
        items.add(new Counterparty(line.split(";")[1]));
      }
    }
    counterpartyService.createAll(items);

    // add 10 new shops
    for (int i = 1; i <= 10; i++) {
      shopService.create(new Shop("s" + i, counterpartyService.get((long) i)));
    }

    // add 2 new manufacturer
    Manufacturer m1 = manufacturerService.create(new Manufacturer("m1"));
    Manufacturer m2 = manufacturerService.create(new Manufacturer("m2"));

    // add 5 new shop types
    shopTypesService.create(new ShopType("st1", m1));
    shopTypesService.create(new ShopType("st2", m1));
    shopTypesService.create(new ShopType("st3", m1));
    shopTypesService.create(new ShopType("st4", m2));
    shopTypesService.create(new ShopType("st5", m2));
  }

  private Resource loadItems() {
    return new ClassPathResource("data/data-dev.csv");
  }
}
