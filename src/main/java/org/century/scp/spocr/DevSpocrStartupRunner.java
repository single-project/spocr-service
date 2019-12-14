package org.century.scp.spocr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.counterparties.models.domain.Counterparty;
import org.century.scp.spocr.counterparties.repositories.CounterpartyRepository;
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
  @Autowired private CounterpartyRepository repository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<Counterparty> items = new ArrayList<>();
    Resource resource = loadItems();

    InputStream stream = resource.getInputStream();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

      String line;
      while ((line = reader.readLine()) != null) {
        items.add(new Counterparty(null, line.split(";")[1], true, (long) 0));
      }
    }
    repository.saveAll(items);
  }

  private Resource loadItems() {
    return new ClassPathResource("data/data-dev.csv");
  }
}
