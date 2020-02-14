package unit.org.century.scp.spocr.mappers.factories;

import java.util.ArrayList;
import java.util.List;
import org.century.scp.spocr.classifier.models.dto.ClassifierView;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

public class ManufacturerFactoryService {

  public Manufacturer createManufacturer(long manufacturerId) {
    return new Manufacturer(manufacturerId, "manufacturer" + manufacturerId, true, (long) 0);
  }

  public List<ShopType> createShopTypes(Manufacturer manufacturer, int size) {
    List<ShopType> shopTypes = new ArrayList<>();
    for (long i = 1; i <= size; i++) {
      ShopType shopType = new ShopType(i, "shopType" + i, true, 0, manufacturer);
      shopTypes.add(shopType);
    }

    return shopTypes;
  }

  public ManufacturerView createManufacturerView(long manufacturerId) {
    return new ManufacturerView(manufacturerId, "manufacturer" + manufacturerId, (long) 0, true);
  }

  public List<ClassifierView> createShopTypeViews(ManufacturerView manufacturer, int size) {
    List<ClassifierView> shopTypes = new ArrayList<>();
    for (long i = 1; i <= size; i++) {
      ClassifierView shopType = new ClassifierView(i, "shopType" + i, (long) 0, true, manufacturer);
      shopTypes.add(shopType);
    }

    return shopTypes;
  }
}
