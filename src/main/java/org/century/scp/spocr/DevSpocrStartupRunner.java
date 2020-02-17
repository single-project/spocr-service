package org.century.scp.spocr;

import java.util.LinkedHashMap;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.saleschannel.services.SalesChannelService;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.shoptype.services.ShopTypesService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyService;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.services.LegalTypeService;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerService;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.services.PaymentDetailsService;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class DevSpocrStartupRunner implements ApplicationRunner {

  @Autowired
  private CounterpartyService counterpartyService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ShopTypesService shopTypesService;
  @Autowired
  private ShopService shopService;
  @Autowired private AccessLevelServiceImpl accessLevelService;
  @Autowired private CustomUserDetailsService userDetailsService;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private CustomUserDetailsService users;
  @Autowired
  private LegalTypeService legalTypeService;
  @Autowired
  private PaymentDetailsService paymentDetailsService;
  @Autowired
  private SalesChannelService salesChannelService;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    signInAsUser();

    // add 3 new legal types
    LegalType legalType0 = new LegalType();
    legalType0.setId((long) 0);
    legalType0.setName("Физ.лицо");
    legalType0.setActive(true);

    LegalType legalType1 = new LegalType();
    legalType1.setId((long) 1);
    legalType1.setName("ИП");
    legalType1.setActive(true);
    legalType1.setOkpfId("50101");
    legalType1.setOkpfType("2014");
    legalType1.setOkfpShortName("ИП");
    legalType1.setOkfpFullName("Индивидуальный предприниматель");

    LegalType legalType2 = new LegalType();
    legalType2.setId((long) 2);
    legalType2.setName("ООО");
    legalType2.setActive(true);
    legalType2.setOkpfType("2014");
    legalType2.setOkpfId("12300");
    legalType2.setOkfpShortName("ООО");
    legalType2.setOkfpFullName("Общество с ограниченной ответственностью");

    legalType0 = legalTypeService.create(legalType0);
    legalType1 = legalTypeService.create(legalType1);
    legalType2 = legalTypeService.create(legalType2);

    // add 10 new counteragent
    for (int i = 1; i <= 10; i++) {
      PaymentDetails paymentDetails = new PaymentDetails();
      paymentDetails.setBic("000");
      paymentDetails.setBank("444");
      paymentDetails.setActive(i % 2 == 0);
      paymentDetails.setVersion((long) 0);
      paymentDetails.setPaymentAccount("111");
      paymentDetails.setCorrespondingAccount("222");

      Counterparty e = new Counterparty("Контагент" + i);
      e.setLegalType(legalType1);
      e.setPaymentDetails(paymentDetails);
      counterpartyService.create(e);
    }

    // add 2 new manufacturer
    Manufacturer m1 = manufacturerService.create(new Manufacturer("ООО Производитель1", true));
    Manufacturer m2 = manufacturerService.create(new Manufacturer("ООО Производитель2", false));

    // add 5 new shop types
    shopTypesService.create(new ShopType("Супермаркет", m1, true));
    shopTypesService.create(new ShopType("Маркет", m1, true));
    shopTypesService.create(new ShopType("Магазин", m1, false));
    shopTypesService.create(new ShopType("Ларек", m2, false));
    shopTypesService.create(new ShopType("Прилавок", m2, true));

    // add 5 new sales channel
    salesChannelService.create(new SalesChannel("Канал продаж1", m1, true));
    salesChannelService.create(new SalesChannel("Канал продаж2", m2, true));
    salesChannelService.create(new SalesChannel("Канал продаж3", m1, false));
    salesChannelService.create(new SalesChannel("Канал продаж4", m2, true));
    salesChannelService.create(new SalesChannel("Канал продаж5", m1, false));

    // add 100 new shops
    for (int i = 1; i <= 100; i++) {
      Shop shop =
          new Shop(
              "Магазин" + i,
              counterpartyService.get(new Random().nextInt(9) + 1),
              shopTypesService.get(new Random().nextInt(5) + 1),
              salesChannelService.get(new Random().nextInt(5) + 1));
      Address address = new Address("address" + i);
      LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
      suggestion.put(i, "s" + i);
      suggestion.put("s" + i, i);
      address.setSuggestion(suggestion);
      shop.setAddress(address);
      shop.setActive(i % 2 == 0);
      shopService.create(shop);
    }

  }

  public void signInAsUser() {
    // ***** add system role - MANAGER ******
    SystemRole role = accessLevelService.createRole("ROLE_MANAGER");

    // add possible rules
    SystemRule canReadRule = accessLevelService.createRule("READ_PRIVILEGE", EntityType.SHOP);
    SystemRule canCreateRule =
        accessLevelService.createRule("CREATE_PRIVILEGE", EntityType.SHOP_TYPE);
    SystemRule canUpdateRule =
        accessLevelService.createRule("UPDATE_PRIVILEGE", EntityType.SHOP_TYPE);

    // link role to rules
    accessLevelService.addRuleToRole(role.getId(), canReadRule.getId());
    accessLevelService.addRuleToRole(role.getId(), canCreateRule.getId());
    accessLevelService.addRuleToRole(role.getId(), canUpdateRule.getId());

    // link user to role
    userDetailsService.addRole(1, role);

    // ***** add system role - READER ******
    role = accessLevelService.createRole("ROLE_READER");

    // link role to rules
    accessLevelService.addRuleToRole(role.getId(), canReadRule.getId());

    // link user to role
    userDetailsService.addRole(2, role);

    SecurityUser user = users.findUserByLogin("user");
    Authentication auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getLogin(), "111111", user.getAuthorities()));

    SecurityContext sc = SecurityContextHolder.getContext();
    sc.setAuthentication(auth);
  }
}
