package unit.org.century.scp.spocr.mappers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.counterparty.mappers.CounterpartyMapper;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.counterparty.models.dto.RequestForCreateCounterparty;
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
import unit.org.century.scp.spocr.mappers.configs.SpringMappersConfig;
import unit.org.century.scp.spocr.mappers.factories.CounterpartyFactoryService;

@ContextConfiguration(classes = SpringMappersConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CounterpartyMapperTest {

  @Autowired
  private CounterpartyMapper counterpartyMapper;
  @Autowired
  private CounterpartyFactoryService counterpartyFactoryService;

  @Test
  public void correctMapNullShortNameToName() {
    RequestForCreateCounterparty view = counterpartyFactoryService
        .createCounterpartyRequestForCreate();
    view.getLegalRekv().setShortName(null);
    Counterparty counterparty = counterpartyMapper.map(view);
    assertEquals(view.getName(), counterparty.getName());
  }

  @Test
  public void correctMapViewToEntityWithParentId() {
    long parentId = 100;
    RequestForCreateCounterparty view = counterpartyFactoryService
        .createCounterpartyRequestForCreate();
    view.setParent(new BaseEntityListView(parentId, "parent"));
    Counterparty counterparty = counterpartyMapper.map(view);
    assertThat(counterparty.getParent().getId(), is(parentId));
  }

  @Test
  public void correctMapEntityToViewWithParentId() {
    long counterpartyId = 1;
    long parentId = 100;
    Counterparty entity = counterpartyFactoryService.createCounterparty(counterpartyId);
    entity.setParent(counterpartyFactoryService.createCounterparty(parentId));
    CounterpartyView view = counterpartyMapper.map(entity);
    assertThat(view.getParent().getId(), is(parentId));
  }

  @Test
  public void correctMapyWithLegalRekvViewToCounterparty() {
    RequestForCreateCounterparty view = counterpartyFactoryService
        .createCounterpartyRequestForCreate();
    Counterparty counterparty = counterpartyMapper.map(view);

    assertEquals(view.getActive(), counterparty.getActive());
    assertEquals(view.getLegalRekv().getShortName(), counterparty.getName());
    assertEquals(view.getLegalRekv().getInn(), counterparty.getInn());
  }

  @Test
  public void correctMapCounterpartyToView() {
    long counterpartyId = 1;
    Counterparty counterparty = counterpartyFactoryService.createCounterparty(counterpartyId);

    CounterpartyView counterpartyView = counterpartyMapper.map(counterparty);
    assertEquals(counterparty.getId(), counterpartyView.getId());
    assertEquals(counterparty.getName(), counterpartyView.getName());
    assertEquals(counterparty.getActive(), counterpartyView.getActive());
    assertEquals(counterparty.getFullName(), counterpartyView.getLegalRekv().getFullName());
    assertEquals(counterparty.getInn(), counterpartyView.getLegalRekv().getInn());
    assertEquals(counterparty.getKpp(), counterpartyView.getLegalRekv().getKpp());
    assertEquals(counterparty.getOgrn(), counterpartyView.getLegalRekv().getOgrn());
    assertEquals(counterparty.getOgrnDate(), counterpartyView.getLegalRekv().getOgrnDate());
    assertEquals(
        counterparty.getOgrnAuthority(), counterpartyView.getLegalRekv().getOgrnAuthority());
    assertEquals(counterparty.getOkpo(), counterpartyView.getLegalRekv().getOkpo());
    assertEquals(counterparty.getOkonh(), counterpartyView.getLegalRekv().getOkonh());
  }

  @Test
  public void correctMapPageToPageView() {
    int size = 3;
    List<Counterparty> counterparties = counterpartyFactoryService.createCounterpartyList(size);

    PageImpl<Counterparty> counterpartyPage =
        new PageImpl<>(counterparties, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), size);
    Page<CounterpartyView> counterpartyViews = counterpartyMapper.map(counterpartyPage);

    assertEquals(counterpartyPage.getTotalElements(), counterpartyViews.getTotalElements());
    assertEquals(counterpartyViews.getContent().size(), counterpartyViews.getContent().size());
  }
}
