package unit.org.century.scp.spocr.mappers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.shop.mappers.ShopMapper;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.RequestForCreateShop;
import org.century.scp.spocr.shop.models.dto.ShopView;
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
import unit.org.century.scp.spocr.mappers.factories.ManufacturerFactoryService;
import unit.org.century.scp.spocr.mappers.factories.ShopFactoryService;

@ContextConfiguration(classes = SpringMappersConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShopMapperTest {

  @Autowired
  private ShopMapper shopMapper;
  @Autowired
  private ShopFactoryService shopFactoryService;
  @Autowired
  private CounterpartyFactoryService counterpartyFactoryService;
  @Autowired
  private ManufacturerFactoryService manufacturerFactoryService;

  @Test
  public void correctMapShopToView() {
    long manufacturerId = 1;
    Manufacturer manufacturer = manufacturerFactoryService.createManufacturer(manufacturerId);
    int size = 2;
    List<ShopType> shopTypes = manufacturerFactoryService.createShopTypes(manufacturer, size);
    long counterpartyId = 10;
    Counterparty counterparty = counterpartyFactoryService.createCounterparty(counterpartyId);
    long shopId = 100;
    Shop shop = shopFactoryService.createShop(shopId, counterparty, shopTypes);
    ShopView shopView = shopMapper.map(shop);

    assertTrue(shopView.getActive());
    assertThat(shopView.getShopTypes().size(), is(2));
    assertThat(shopView.getCounterparty().getId(), is(counterparty.getId()));
    assertTrue(shopView.getAddress().getActive());
    assertEquals(shop.getAddress().getAddress(), shopView.getAddress().getAddress());
    assertThat(
        shopView.getAddress().getSuggestion().get("value"),
        is(shop.getAddress().getSuggestion().get("value")));
  }

  @Test
  public void correctMapPageToPageView() {
    long manufacturerId = 1;
    Manufacturer manufacturer = manufacturerFactoryService.createManufacturer(manufacturerId);
    int size = 2;
    List<ShopType> shopTypes = manufacturerFactoryService.createShopTypes(manufacturer, size);
    long counterpartyId = 10;
    Counterparty counterparty = counterpartyFactoryService.createCounterparty(counterpartyId);
    size = 2;
    List<Shop> shops = shopFactoryService.createShop(size, counterparty, shopTypes);
    PageImpl<Shop> shopPage =
        new PageImpl<>(shops, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), size);
    Page<ShopView> shopViews = shopMapper.map(shopPage);

    assertEquals(shopPage.getTotalElements(), shopViews.getTotalElements());
    assertEquals(shopPage.getContent().size(), shopViews.getContent().size());
  }

  @Test
  public void correctMapViewToShop() {
    long manufacturerId = 1;
    ManufacturerView manufacturerView =
        manufacturerFactoryService.createManufacturerView(manufacturerId);

    int size = 2;
    List<ClassifierView> shopTypeViews =
        manufacturerFactoryService.createShopTypeViews(manufacturerView, size);
    long counterpartyId = 100;
    CounterpartyView counterpartyView =
        counterpartyFactoryService.createCounterpartyRequestForUpdate(counterpartyId);
    AddressView addressView = shopFactoryService.createAddressView();
    RequestForCreateShop shopView =
        shopFactoryService.createShopRequestForCreate(shopTypeViews, counterpartyView, addressView);

    Shop shop = shopMapper.map(shopView);
    assertEquals(shopView.getId(), shop.getId());
    assertEquals(shopView.getVersion(), shop.getVersion());
    assertEquals(shopView.getName(), shop.getName());
    assertEquals(shopView.getActive(), shop.getActive());
    assertThat(shop.getShopTypes().size(), is(size));
  }
}
