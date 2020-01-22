package org.century.scp.spocr;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.services.CounterpartyServiceImpl;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerServiceImpl;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.models.dto.AuthenticationResponse;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
import org.century.scp.spocr.security.services.JwtTokenProvider;
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
  @Autowired private JwtTokenProvider jwtTokenProvider;

  @Override
  public void run(ApplicationArguments args) throws Exception {
      signInAsUser();

    // add 10 new counteragent
    List<Counterparty> counterparties = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      counterparties.add(new Counterparty("Контагент" + i));
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

    // add 1000 new shops
    List<Shop> shops = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) {
      shops.add(
          new Shop(
              "Магазин" + i,
              counterpartyService.get(new Random().nextInt(9) + 1),
              shopTypesService.get(new Random().nextInt(5) + 1)));
    }
    shopService.createAll(shops);



  }

  public void signInAsUser() {
    // ***** add system role - MANAGER ******
    SystemRole role = accessLevelService.createRole("ROLE_MANAGER");

    // add possible rules
    SystemRule canReadRule = accessLevelService.createRule("READ_PRIVILEGE", EntityType.SHOP);
    SystemRule canCreateRule = accessLevelService.createRule("CREATE_PRIVILEGE", EntityType.SHOP_TYPE);
    SystemRule canUpdateRule = accessLevelService.createRule("UPDATE_PRIVILEGE", EntityType.SHOP_TYPE);

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
    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getLogin(), "111111", user.getAuthorities()));

    SecurityContext sc = SecurityContextHolder.getContext();
    sc.setAuthentication(auth);
  }

}
