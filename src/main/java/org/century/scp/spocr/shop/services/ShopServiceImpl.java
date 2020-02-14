package org.century.scp.spocr.shop.services;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.saleschannel.services.SalesChannelServiceImpl;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.shoptype.services.ShopTypesServiceImpl;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.repositories.ShopRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopServiceImpl extends BaseService<Shop> {

  private ShopTypesServiceImpl shopTypesService;
  private SalesChannelServiceImpl salesChannelService;
  private CounterpartyServiceImpl counterpartyService;

  @Autowired
  public ShopServiceImpl(
      DefaultMessageSource messageSource,
      ShopRepository shopRepository,
      ShopTypesServiceImpl shopTypesService,
      SalesChannelServiceImpl salesChannelService,
      CounterpartyServiceImpl counterpartyService) {
    super(messageSource, shopRepository);
    this.shopTypesService = shopTypesService;
    this.salesChannelService = salesChannelService;
    this.counterpartyService = counterpartyService;
  }

  @Override
  public Shop initialize(Shop shop) {
    Hibernate.initialize(shop.getShopTypes());
    Hibernate.initialize(shop.getSalesChannels());
    return shop;
  }

  @Override
  public Shop assemble(Shop entity) {
    // counterparty
    if (entity.getCounterparty() != null) {
      entity.setCounterparty(counterpartyService.get(entity.getCounterparty().getId()));
    }

    // shop types
    if (entity.linkedWithShopTypes()) {
      List<ShopType> shopTypes = shopTypesService.getAll(entity.getShopTypes());
      entity.setShopTypes(shopTypes);
    }

    // sales channels
    if (entity.linkedWithSalesChannel()) {
      List<SalesChannel> salesChannels = salesChannelService.getAll(entity.getSalesChannels());
      entity.setSalesChannels(salesChannels);
    }

    return entity;
  }

  @Override
  public Class<Shop> getEntityClass() {
    return Shop.class;
  }
}
