package org.century.scp.spocr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.repositories.CounterpartyRepository;
import org.century.scp.spocr.counterparties.services.CounterpartyServiceImpl;
import org.century.scp.spocr.manufactures.models.domain.Manufacturer;
import org.century.scp.spocr.manufactures.repositories.ManufacturerRepository;
import org.century.scp.spocr.shops.models.domain.Shop;
import org.century.scp.spocr.shops.services.ShopServiceImpl;
import org.century.scp.spocr.shoptypes.models.domain.ShopType;
import org.century.scp.spocr.shoptypes.repositories.ShopTypeRepository;
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
  private CounterpartyRepository counterpartyRepository;
  @Autowired
  private CounterpartyServiceImpl counterpartyService;
  @Autowired
  private ManufacturerRepository manufacturerRepository;
  @Autowired
  private ShopTypeRepository shopTypeRepository;
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
        items.add(new Counterparty(null, line.split(";")[1], true));
      }
    }
    counterpartyRepository.saveAll(items);

    // add 10 new shops
    for (int i = 1; i <= 10; i++) {
      shopService.create(new Shop("s" + i, counterpartyService.get((long) i)));
    }

    // add 2 new manufacturer
    Manufacturer m1 = manufacturerRepository.save(new Manufacturer("m1", true));
    Manufacturer m2 = manufacturerRepository.save(new Manufacturer("m2", true));

    // add 5 new shop types
    shopTypeRepository.save(new ShopType("st1", m1));
    shopTypeRepository.save(new ShopType("st2", m1));
    shopTypeRepository.save(new ShopType("st3", m1));
    shopTypeRepository.save(new ShopType("st4", m2));
    shopTypeRepository.save(new ShopType("st5", m2));
  }

  private Resource loadItems() {
    return new ClassPathResource("data/data-dev.csv");
  }
}
