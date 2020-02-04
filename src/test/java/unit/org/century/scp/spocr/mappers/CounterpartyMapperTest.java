package unit.org.century.scp.spocr.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringMappersConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CounterpartyMapperTest {

  @Autowired
  private CounterpartyMapper counterpartyMapper;

  @Test
  public void correctMapCounterpartyToView() {
    PaymentDetails paymentDetails = new PaymentDetails((long) 1, "", "", "", "", true);
    Counterparty counterparty = new Counterparty((long) 1, "name1", paymentDetails, true, null);
    CounterpartyView counterpartyView = counterpartyMapper.map(counterparty);

    assertEquals(counterparty.getId(), counterpartyView.getId());
    assertEquals(counterparty.getName(), counterpartyView.getName());
    assertEquals(counterparty.getActive(), counterpartyView.getActive());
  }

  @Test
  public void correctMapPageToPageView() {
    PaymentDetails paymentDetails1 = new PaymentDetails((long) 1, "", "", "", "", true);
    PaymentDetails paymentDetails2 = new PaymentDetails((long) 2, "", "", "", "", true);
    PaymentDetails paymentDetails3 = new PaymentDetails((long) 3, "", "", "", "", true);
    Counterparty counterparty1 =
        new Counterparty((long) 1, "name1", paymentDetails1, true, (long) 10);
    Counterparty counterparty2 =
        new Counterparty((long) 2, "name2", paymentDetails2, true, (long) 10);
    Counterparty counterparty3 =
        new Counterparty((long) 3, "name3", paymentDetails3, true, (long) 10);
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
