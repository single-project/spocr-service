package unit.org.century.scp.spocr.mappers.factories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.address.models.dto.AddressView;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.CounterpartyView;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.RequestForCreateShop;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.models.dto.ShopTypeView;

public class ShopFactoryService {

  public List<Shop> createShop(int size, Counterparty counterparty, List<ShopType> shopTypes) {
    List<Shop> shops = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      shops.add(createShop((long) i * 100, counterparty, shopTypes));
    }
    return shops;
  }

  public Shop createShop(long shopId, Counterparty counterparty, List<ShopType> shopTypes) {
    return new Shop(shopId, "shop" + shopId, counterparty, shopTypes, createAddress(shopId), true);
  }

  public Address createAddress(long shopId) {
    String address = "344002 Россия, г. Ростов-на-Дону, ул. Текучева, д. 36, кв. 25";
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put("value", address);
    return new Address(shopId * 1000, address, "comment" + shopId * 1000, suggestion, true);
  }

  public RequestForCreateShop createShopRequestForCreate(
      List<ShopTypeView> shopTypeViews, CounterpartyView counterparty, AddressView address) {
    int randomInt = new Random().nextInt(9);
    return new RequestForCreateShop("shop" + randomInt, true, shopTypeViews, counterparty, address);
  }

  public AddressView createAddressView() {
    String address = "344002 Россия, г. Ростов-на-Дону, ул. Текучева, д. 36, кв. 25";
    LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
    suggestion.put("value", address);
    return new AddressView(address, suggestion);
  }
}
