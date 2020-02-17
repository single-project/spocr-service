package org.century.scp.spocr.shop.services;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.mappers.MapperI;
import org.century.scp.spocr.base.servicefacades.BaseServiceFacade;
import org.century.scp.spocr.base.services.ServiceI;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.models.dto.ShopView;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShopServiceFacade extends BaseServiceFacade<Shop, ShopView> {

  public ShopServiceFacade(
      ServiceI<Shop> service,
      MapperI<Shop, ShopView> mapper, EventRepositoryImpl eventRepository) {
    super(service, mapper, eventRepository);
  }
}
