package unit.org.century.scp.spocr.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class CounterpartyMapperTest {

  private CounterpartyMapper counterpartyMapper;

  @Before
  public void setUp() throws Exception {
    counterpartyMapper = Mappers.getMapper(CounterpartyMapper.class);
  }

  @Test
  public void correctMapCounterpartyToView() {
    Counterparty counterparty = new Counterparty((long) 1, "name1", true, null);
    CounterpartyView counterpartyView = counterpartyMapper.map(counterparty);

    assertEquals(counterparty.getId(), counterpartyView.getId());
    assertEquals(counterparty.getName(), counterpartyView.getName());
    assertEquals(counterparty.getActive(), counterpartyView.getActive());
  }

  @Test
  public void correctMapPageToPageView() {
    Counterparty counterparty1 = new Counterparty((long) 1, "name1", true, (long) 10);
    Counterparty counterparty2 = new Counterparty((long) 2, "name2", true, (long) 10);
    Counterparty counterparty3 = new Counterparty((long) 3, "name3", true, (long) 10);
    List<Counterparty> manufacturers = new ArrayList<>();
    manufacturers.add(counterparty1);
    manufacturers.add(counterparty2);
    manufacturers.add(counterparty3);
    PageImpl<Counterparty> counterpartyPage =
        new PageImpl<>(manufacturers, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), 3);
    Page<CounterpartyView> counterpartyViews = counterpartyMapper.map(counterpartyPage);

    assertEquals(counterpartyPage.getTotalElements(), counterpartyViews.getTotalElements());
    assertEquals(counterpartyViews.getContent().size(), counterpartyViews.getContent().size());
  }

  @Test
  public void correctMapViewToCounterparty() {
    CounterpartyView counterpartyView = new CounterpartyView("name");
    Counterparty counterparty = counterpartyMapper.map(counterpartyView);

    assertNull(counterparty.getId());
    assertNull(counterparty.getVersion());
    assertTrue(counterparty.getActive());
    assertEquals(counterpartyView.getName(), counterparty.getName());
  }
}
