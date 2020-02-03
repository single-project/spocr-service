package unit.org.century.scp.spocr.mappers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.shop.mappers.ShopMapper;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;
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
public class ShopMapperTest {

  @Autowired
  private ShopMapper shopMapper;

  @Test
  public void correctMapShopToView() {
    Manufacturer manufacturer = new Manufacturer((long) 1, "m1", true, (long) 1);
    ShopType shopType1 = new ShopType((long) 2, "st2", true, (long) 1, manufacturer);
    ShopType shopType2 = new ShopType((long) 3, "st3", true, (long) 1, manufacturer);
    List<ShopType> shopTypes = new ArrayList<>();
    shopTypes.add(shopType1);
    shopTypes.add(shopType2);
    String adr = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    Address address = new Address(adr);
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put(1, "s" + 1);
    suggestion.put("s" + 1, 1);
    address.setSuggestion(suggestion);
    Counterparty counterparty = new Counterparty((long) 4, "c4", true, (long) 1);
    Shop shop = new Shop((long) 5, "s5", true, (long) 0, counterparty, shopTypes);
    shop.setAddress(address);
    ShopView shopView = shopMapper.map(shop);

    assertTrue(shopView.getActive());
    assertThat(shopView.getShopTypes().size(), is(2));
    assertThat(shopView.getCounterparty().getId(), is(counterparty.getId()));
    assertTrue(shopView.getAddress().getActive());
    assertEquals(adr, shopView.getAddress().getAddress());
    assertThat(shopView.getAddress().getSuggestion().size(), is(2));
    assertThat(shopView.getAddress().getSuggestion().get(1), is("s1"));
  }

  @Test
  public void correctMapPageToPageView() {
    Manufacturer manufacturer = new Manufacturer((long) 1, "m1", true, (long) 1);
    ShopType shopType1 = new ShopType((long) 2, "st2", true, (long) 1, manufacturer);
    ShopType shopType2 = new ShopType((long) 3, "st3", true, (long) 1, manufacturer);
    List<ShopType> shopTypes = new ArrayList<>();
    shopTypes.add(shopType1);
    shopTypes.add(shopType2);
    Counterparty counterparty = new Counterparty((long) 4, "c4", true, (long) 1);
    Shop shop1 = new Shop((long) 5, "s5", true, (long) 0, counterparty, shopTypes);
    Shop shop2 = new Shop((long) 6, "s6", true, (long) 0, counterparty, shopTypes);
    List<Shop> shops = new ArrayList<>();
    shops.add(shop1);
    shops.add(shop2);
    PageImpl<Shop> shopPage =
        new PageImpl<>(shops, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), 3);
    Page<ShopView> shopViews = shopMapper.map(shopPage);

    assertEquals(shopPage.getTotalElements(), shopViews.getTotalElements());
    assertEquals(shopPage.getContent().size(), shopViews.getContent().size());
  }

  @Test
  public void correctMapViewToShop() {
    ManufacturerView manufacturerView = new ManufacturerView((long) 1, "m1", (long) 0, true);
    ShopTypeView shopTypeView1 =
        new ShopTypeView((long) 2, "st2", (long) 0, true, manufacturerView);
    ShopTypeView shopTypeView2 =
        new ShopTypeView((long) 3, "st3", (long) 0, true, manufacturerView);
    List<ShopTypeView> shopTypeViews = new ArrayList<>();
    shopTypeViews.add(shopTypeView1);
    shopTypeViews.add(shopTypeView2);
    CounterpartyView counterpartyView = new CounterpartyView((long) 4, "c4", (long) 0, true);
    ShopView shopView =
        new ShopView((long) 5, "s5", (long) 0, true, shopTypeViews, counterpartyView);

    Shop shop = shopMapper.map(shopView);

    assertEquals(shopView.getId(), shop.getId());
    assertEquals(shopView.getVersion(), shop.getVersion());
    assertEquals(shopView.getName(), shop.getName());
    assertEquals(shopView.getActive(), shop.getActive());
    assertThat(shop.getShopTypes().size(), is(2));
  }
}
