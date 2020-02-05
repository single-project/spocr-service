package unit.org.century.scp.spocr.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.manufacturer.mappers.ManufacturerMapper;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;
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

@ContextConfiguration(classes = SpringMappersConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerMapperTest {

  @Autowired
  private ManufacturerMapper manufacturerMapper;

  @Test
  public void correctMapManufacturerToView() {
    Manufacturer manufacturer = new Manufacturer((long) 1, "name", true, (long) 10);
    ManufacturerView manufacturerView = manufacturerMapper.map(manufacturer);

    assertEquals(manufacturerView.getId(), manufacturer.getId());
    assertEquals(manufacturerView.getVersion(), manufacturer.getVersion());
  }

  @Test
  public void correctMapPageToPageView() {
    Manufacturer manufacturer1 = new Manufacturer((long) 1, "name1", true, (long) 10);
    Manufacturer manufacturer2 = new Manufacturer((long) 2, "name2", true, (long) 10);
    Manufacturer manufacturer3 = new Manufacturer((long) 3, "name3", true, (long) 10);
    List<Manufacturer> manufacturers = new ArrayList<>();
    manufacturers.add(manufacturer1);
    manufacturers.add(manufacturer2);
    manufacturers.add(manufacturer3);
    PageImpl<Manufacturer> manufacturerPage =
        new PageImpl<>(manufacturers, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), 3);
    Page<ManufacturerView> manufacturerViewPage = manufacturerMapper.map(manufacturerPage);

    assertEquals(manufacturerPage.getTotalElements(), manufacturerViewPage.getTotalElements());
    assertEquals(manufacturerPage.getContent().size(), manufacturerViewPage.getContent().size());
  }

  @Test
  public void correctViewToManufacturer() {
    ManufacturerView manufacturerView = new ManufacturerView(("name"));
    Manufacturer manufacturer = manufacturerMapper.map(manufacturerView);

    assertNull(manufacturer.getId());
    assertNull(manufacturer.getVersion());
    assertTrue(manufacturer.getActive());
    assertEquals(manufacturer.getName(), manufacturerView.getName());
  }
}
