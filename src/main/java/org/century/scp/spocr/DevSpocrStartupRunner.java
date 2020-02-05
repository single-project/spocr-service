package org.century.scp.spocr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.services.LegalTypeServiceImpl;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.services.PaymentDetailsServiceImpl;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.services.ShopServiceImpl;
import org.century.scp.spocr.shoptype.models.domain.ShopType;
import org.century.scp.spocr.shoptype.services.ShopTypesServiceImpl;
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

@Slf4j
@Component
@Profile({"dev", "prod"})
public class DevSpocrStartupRunner implements ApplicationRunner {

  @Autowired private CounterpartyServiceImpl counterpartyService;
  @Autowired private ManufacturerServiceImpl manufacturerService;
  @Autowired private ShopTypesServiceImpl shopTypesService;
  @Autowired private ShopServiceImpl shopService;
  @Autowired private AccessLevelServiceImpl accessLevelService;
  @Autowired private CustomUserDetailsService userDetailsService;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private CustomUserDetailsService users;
  @Autowired
  private LegalTypeServiceImpl legalTypeService;
  @Autowired
  private PaymentDetailsServiceImpl paymentDetailsService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    signInAsUser();

    // add 3 new legal types
    LegalType legalType0 = LegalType.builder().name("Физ.лицо").active(true).build();

    LegalType legalType1 =
        LegalType.builder()
            .name("ИП")
            .active(true)
            .okpfType("2014")
            .okpfId("50102")
            .okfpShortName("ИП")
            .okfpFullName("Индивидуальный предприниматель")
            .build();
    LegalType legalType2 =
        LegalType.builder()
            .name("ООО")
            .active(true)
            .okpfType("2014")
            .okpfId("12300")
            .okfpShortName("ООО")
            .okfpFullName("Общество с ограниченной ответственностью")
            .build();

    legalType0 = legalTypeService.create(legalType0);
    legalType1 = legalTypeService.create(legalType1);
    legalType2 = legalTypeService.create(legalType2);

    // add 10 new counteragent
    List<Counterparty> counterparties = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      PaymentDetails paymentDetails =
          PaymentDetails.builder()
              .active(true)
              .bic("000")
              .bank("444")
              .paymentAccount("111")
              .correspondingAccount("222")
              .build();

      Counterparty e = new Counterparty("Контагент" + i);
      e.setLegalType(legalType1);
      e.setPaymentDetails(paymentDetails);
      counterparties.add(e);
    }
    counterpartyService.createAll(counterparties);

    // add 2 new manufacturer
    Manufacturer m1 = manufacturerService.create(new Manufacturer("ООО Производитель1"));
    Manufacturer m2 = manufacturerService.create(new Manufacturer("ООО Производитель2"));

    // add 5 new shop types
    shopTypesService.create(new ShopType("Супермаркет", m1));
    shopTypesService.create(new ShopType("Маркет", m1));
    shopTypesService.create(new ShopType("Магазин", m1));
    shopTypesService.create(new ShopType("Ларек", m2));
    shopTypesService.create(new ShopType("Прилавок", m2));

    // add 100 new shops
    List<Shop> shops = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      Shop shop =
          new Shop(
              "Магазин" + i,
              counterpartyService.get(new Random().nextInt(9) + 1),
              shopTypesService.get(new Random().nextInt(5) + 1));
      Address address = new Address("address" + i);
      LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
      suggestion.put(i, "s" + i);
      suggestion.put("s" + i, i);
      address.setSuggestion(suggestion);
      // address = addressService.create(address);
      shop.setAddress(address);
      shops.add(shop);
    }
    shopService.createAll(shops);
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
