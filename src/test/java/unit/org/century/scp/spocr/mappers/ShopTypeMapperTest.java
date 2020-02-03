package unit.org.century.scp.spocr.mappers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
import org.century.scp.spocr.shoptype.mappers.ShopTypeMapper;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import unit.org.century.scp.spocr.mappers.ShopTypeMapperTest.SpringTestConfig;

@ContextConfiguration(classes = SpringTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShopTypeMapperTest {

  @Autowired
  private ShopTypeMapper shopTypeMapper;

  @Test
  public void correctMapShopTypeToView() {
    Manufacturer manufacturer = new Manufacturer((long) 1, "m1", true, (long) 1);
    ShopType shopType = new ShopType((long) 1, "st1", true, (long) 1, manufacturer);

    ShopTypeView shopTypeView = shopTypeMapper.map(shopType);
    assertEquals(shopType.getId(), shopTypeView.getId());
    assertEquals(shopType.getManufacturer().getId(), shopTypeView.getManufacturer().getId());
  }

  @Test
  public void correctMapPageToPageView() {
    Manufacturer manufacturer = new Manufacturer((long) 1, "m1", true, (long) 1);
    ShopType shopType1 = new ShopType((long) 1, "st1", true, (long) 1, manufacturer);
    ShopType shopType2 = new ShopType((long) 2, "st2", true, (long) 1, manufacturer);
    ShopType shopType3 = new ShopType((long) 3, "st3", true, (long) 1, manufacturer);

    List<ShopType> shopTypes = new ArrayList<>();
    shopTypes.add(shopType1);
    shopTypes.add(shopType2);
    shopTypes.add(shopType3);
    PageImpl<ShopType> shopTypePage =
        new PageImpl<>(shopTypes, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), 3);
    Page<ShopTypeView> shopTypeViews = shopTypeMapper.map(shopTypePage);

    assertEquals(shopTypePage.getTotalElements(), shopTypeViews.getTotalElements());
    assertEquals(shopTypePage.getContent().size(), shopTypeViews.getContent().size());
  }

  @Test
  public void correctMapViewToShopType() {
    ManufacturerView manufacturerView = new ManufacturerView((long) 1, "m1", (long) 0, true);

    ShopTypeView shopTypeView = new ShopTypeView((long) 2, "st1", (long) 0, true, manufacturerView);
    ShopType shopType = shopTypeMapper.map(shopTypeView);

    assertEquals(shopTypeView.getId(), shopType.getId());
    assertEquals(shopTypeView.getVersion(), shopType.getVersion());
    assertEquals(shopTypeView.getName(), shopType.getName());
    assertEquals(shopTypeView.getActive(), shopType.getActive());
  }

  @Configuration
  @ComponentScan(basePackageClasses = ShopTypeMapperTest.class)
  public static class SpringTestConfig {

    @Bean
    public ManufacturerMapper manufacturerMapper() {
      return Mappers.getMapper(ManufacturerMapper.class);
    }

    @Bean
    public ShopTypeMapper shopTypeMapper() {
      return Mappers.getMapper(ShopTypeMapper.class);
    }
  }
}
