package org.century.scp.spocr.shop.services;

import java.util.Set;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopService extends BaseService<Shop> {

  public ShopService(EntityManager entityManager, BaseRepository<Shop> repository) {
    super(Shop.class, entityManager, repository);
  }

  public void refresh(Shop shop) {
    // counterparty
    if (shop.getCounterparty() != null) {
      shop.setCounterparty(getReference(shop.getCounterparty(), Counterparty.class));
    }

    // shop types
    if (shop.linkedWithShopTypes()) {
      Set<ShopType> shopTypes = getReferences(shop.getShopTypes(), ShopType.class);
      shop.setShopTypes(shopTypes);
    }

    // sales channels
    if (shop.linkedWithSalesChannel()) {
      Set<SalesChannel> salesChannels = getReferences(shop.getSalesChannels(), SalesChannel.class);
      shop.setSalesChannels(salesChannels);
    }
  }
}
