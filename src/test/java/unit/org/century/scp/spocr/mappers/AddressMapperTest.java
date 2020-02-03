package unit.org.century.scp.spocr.mappers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.century.scp.spocr.address.mappers.AddressMapper;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.models.dto.AddressView;
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
public class AddressMapperTest {

  @Autowired
  private AddressMapper addressMapper;

  @Test
  public void correctMapAddressToView() {
    String adr = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    Address address = new Address(adr);
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put(1, "s" + 1);
    suggestion.put("s" + 1, 1);
    address.setSuggestion(suggestion);
    AddressView addressView = addressMapper.map(address);

    assertTrue(addressView.getActive());
    assertEquals(adr, addressView.getAddress());
    assertThat(addressView.getSuggestion().size(), is(2));
    assertThat(addressView.getSuggestion().get(1), is("s1"));
  }

  @Test
  public void correctMapPageToPageView() {
    List<Address> addresses = new ArrayList<>();
    String adr1 = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    Address address1 = new Address(adr1);
    LinkedHashMap<Object, Object> suggestion1 = new LinkedHashMap<>();
    suggestion1.put(1, "s" + 1);
    suggestion1.put("s" + 1, 1);
    address1.setSuggestion(suggestion1);
    addresses.add(address1);

    String adr2 = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    Address address2 = new Address(adr2);
    LinkedHashMap<Object, Object> suggestion2 = new LinkedHashMap<>();
    suggestion2.put(2, "s" + 2);
    suggestion2.put("s" + 2, 2);
    address2.setSuggestion(suggestion2);
    addresses.add(address2);
    PageImpl<Address> addressPage =
        new PageImpl<>(addresses, PageRequest.of(0, 10, Sort.by(Direction.ASC, "id")), 3);

    Page<AddressView> addressViews = addressMapper.map(addressPage);

    assertEquals(addressPage.getTotalElements(), addressViews.getTotalElements());
    assertEquals(addressPage.getContent().size(), addressViews.getContent().size());
  }

  @Test
  public void correctMapViewToAddress() {
    String adr = "344002 Россия, г. Ростов-на-Дону, д. 36, кв. 25";
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put(1, "s" + 1);
    suggestion.put("s" + 1, 1);
    AddressView addressView = new AddressView(adr, suggestion);
    long i = 194;
    addressView.setId(i);

    Address address = addressMapper.map(addressView);
    assertThat(address.getId(), is(i));
    assertEquals(adr, address.getAddress());
    assertThat(address.getSuggestion().size(), is(2));
    assertThat(address.getSuggestion().get(1), is("s1"));
  }

}
