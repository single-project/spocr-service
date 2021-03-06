package org.century.scp.spocr;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
import org.century.scp.spocr.address.models.domain.Address;
import org.century.scp.spocr.app.services.AppServiceImpl;
import org.century.scp.spocr.classifier.saleschannel.models.domain.SalesChannel;
import org.century.scp.spocr.classifier.saleschannel.services.SalesChannelService;
import org.century.scp.spocr.classifier.shopdepart.domain.ShopDepart;
import org.century.scp.spocr.classifier.shopdepart.services.ShopDepartService;
import org.century.scp.spocr.classifier.shoptype.models.domain.ShopType;
import org.century.scp.spocr.classifier.shoptype.services.ShopTypesService;
import org.century.scp.spocr.classifier.specialization.domain.ShopSpecialization;
import org.century.scp.spocr.classifier.specialization.services.ShopSpecializationtService;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.contact.models.domain.ContactRole;
import org.century.scp.spocr.contact.services.ContactRoleService;
import org.century.scp.spocr.contact.services.ContactService;
import org.century.scp.spocr.contract.models.domain.Contract;
import org.century.scp.spocr.contract.models.domain.SubContract;
import org.century.scp.spocr.contract.services.ContractService;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.domain.ExtRegSystemCounterpartyProperties;
import org.century.scp.spocr.counterparty.models.domain.LegalRekv;
import org.century.scp.spocr.counterparty.services.CounterpartyService;
import org.century.scp.spocr.enumeration.services.EnumerationService;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;
import org.century.scp.spocr.extregsystem.services.ExtRegSystemService;
import org.century.scp.spocr.manufacturer.models.domain.Manufacturer;
import org.century.scp.spocr.manufacturer.services.ManufacturerService;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.century.scp.spocr.owner.services.OwnerService;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.person.models.domain.Person;
import org.century.scp.spocr.person.services.PersonService;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
import org.century.scp.spocr.shop.models.domain.ExtRegSystemShopProperties;
import org.century.scp.spocr.shop.models.domain.Shop;
import org.century.scp.spocr.shop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile({"dev"})
public class DevSpocrStartupRunner implements ApplicationRunner {

  @Autowired private CounterpartyService counterpartyService;
  @Autowired private ManufacturerService manufacturerService;

  @Autowired private ShopService shopService;
  @Autowired private AccessLevelServiceImpl accessLevelService;
  @Autowired private CustomUserDetailsService userDetailsService;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private CustomUserDetailsService users;

  @Autowired private ContractService contractService;
  @Autowired private PersonService personService;
  @Autowired private EnumerationService enumerationService;
  @Autowired private OwnerService ownerService;
  @Autowired private ShopTypesService shopTypesService;
  @Autowired private SalesChannelService salesChannelService;
  @Autowired private ShopSpecializationtService shopSpecializationtService;
  @Autowired private ShopDepartService shopDepartService;
  @Autowired private ContactRoleService contactRoleService;
  @Autowired private ContactService contactService;
  @Autowired private ExtRegSystemService extRegSystemService;
  @Autowired private AppServiceImpl appService;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    signInAsAdmin();

    // if exception then go
    if (appService.wasInitialized()) {
      return;
    }

    // ***** security settings ******
    SecurityUser user = users.findUserByLogin("user");
    SecurityUser reader = users.findUserByLogin("reader");

    SystemRole adminRole = accessLevelService.getRole("ROLE_ADMIN");
    SystemRole readerRole = accessLevelService.getRole("ROLE_READER");
    SystemRole managerRole = accessLevelService.getRole("ROLE_MANAGER");

    SystemRule canReadRule = accessLevelService.getRule("READ_PRIVILEGE");
    SystemRule canCreateRule = accessLevelService.getRule("CREATE_PRIVILEGE");
    SystemRule canUpdateRule = accessLevelService.getRule("UPDATE_PRIVILEGE");

    accessLevelService.addRuleToRole(readerRole.getId(), canReadRule.getId());
    accessLevelService.addRuleToRole(managerRole.getId(), canReadRule.getId());
    accessLevelService.addRuleToRole(managerRole.getId(), canCreateRule.getId());
    accessLevelService.addRuleToRole(managerRole.getId(), canUpdateRule.getId());

    userDetailsService.addRole(reader.getId(), readerRole);
    userDetailsService.addRole(user.getId(), adminRole, managerRole);

    signInAsUser();

    // add 1 ext reg system
    ExtRegSystem extRegSystem = new ExtRegSystem();
    extRegSystem.setActive(true);
    extRegSystem.setName("Меркурий");
    extRegSystem = extRegSystemService.create(extRegSystem);

    // add 3 new roles
    ContactRole role1 = contactRoleService.create(new ContactRole("Менеджер"));
    ContactRole role2 = contactRoleService.create(new ContactRole("Кладовщик"));
    ContactRole role3 = contactRoleService.create(new ContactRole("Директор"));

    // add 2 new contacts
    Person person1 = personService.create(new Person("Петров", "Иван", "Сидорович"));
    Person person2 = personService.create(new Person("Сидоров", "Петр", "Иванов"));
    Contact c1 = contactService.create(new Contact(role1, person1));
    Contact c2 = contactService.create(new Contact(role2, person2));
    Contact c3 = contactService.create(new Contact(role3, person2));

    // add  owner
    Owner owner = new Owner();
    owner.setName("Владелец");
    owner.setActive(true);
    owner.addContact(c1);
    owner = ownerService.create(owner);

    // add 1 new person
    Person person = new Person();
    person.setLastName("Уик");
    person.setLastName("Джон");
    person.setBirthDate(new Date());
    person.setCitizenship(enumerationService.getByIdentAndValue("CITIZENSHIP", "RU"));
    person.setGender(enumerationService.getByIdentAndValue("GENDER", "MALE"));
    person.setDocType(enumerationService.getByIdentAndValue("DOC-TYPE", "PASSPORT-RF"));
    personService.create(person);

    // add 5 legal rekv
    LegalRekv legalRekv = new LegalRekv();
    legalRekv.setFullName("Индивидуальный предприниматель Иванов Сидр");
    legalRekv.setInn("234234234");
    legalRekv.setOgrn("1167847438220");
    legalRekv.setOgrnAuthority("Орган выдавший ОГРН");
    legalRekv.setOkpo("05561358");
    legalRekv.setShortName("ИП Ивано");

    // add 10 new counteragent
    for (int i = 1; i <= 10; i++) {
      PaymentDetails paymentDetails = new PaymentDetails();
      paymentDetails.setBic("000");
      paymentDetails.setBank("444");
      paymentDetails.setPaymentAccount("111");
      paymentDetails.setCorrespondingAccount("222");

      Counterparty e = new Counterparty("Контагент" + i);
      e.setLegalType(enumerationService.getByIdentAndValue("LEGAL-TYPE", "LEGAL"));
      e.addStatus(enumerationService.getByIdentAndValue("CP-STATUS", "CLIENT"));
      e.setLegalRekv(legalRekv);
      e.setOwner(owner);
      e.addPaymentType(enumerationService.getByIdentAndValue("PAYMENT-TYPE", "CASHLESS"));
      e.setNoVat(true);
      e.addContact(c1);
      ExtRegSystemCounterpartyProperties props = new ExtRegSystemCounterpartyProperties();
      props.setExtRegSystem(extRegSystem);
      Map<String, Object> params = new HashMap<>();
      params.put("GUID", UUID.randomUUID());
      props.setProperties(params);
      e.setExtRegSystemProperties(props);
      e.addPaymentDetails(paymentDetails);
      counterpartyService.create(e);
    }

    // add 2 new contracts
    Page<Counterparty> counterparties = counterpartyService.getPage(PageRequest.of(0, 10));

    Contract contract = new Contract();
    contract.setName("Продажа товаров");
    contract.setCounterparty1(counterparties.getContent().get(0));
    contract.setCounterparty2(counterparties.getContent().get(1));
    contract.setActive(true);
    contract.setContractNumber("123AA");
    contract.setStartDate(new Date());
    contract.setEndDate(Date.from(new Date().toInstant().plus(1, DAYS)));
    contract.setType(enumerationService.getByIdentAndValue("CONTRACT-TYPE", "GOODS"));
    contract.setStatus(enumerationService.getByIdentAndValue("CONTRACT-STATUS", "ACTIVE"));
    SubContract subContract = new SubContract();
    subContract.setActive(true);
    subContract.setName("Доп соглашение 1");
    subContract.setStatus(enumerationService.getByIdentAndValue("CONTRACT-STATUS", "ACTIVE"));
    subContract.setSubContractNumber("AAA111222");
    subContract.setSubContractDate(new Date());
    contract.addSubContract(subContract);
    SubContract subContract2 = new SubContract();
    subContract2.setActive(true);
    subContract2.setName("Доп соглашение 2");
    subContract2.setStatus(enumerationService.getByIdentAndValue("CONTRACT-STATUS", "ACTIVE"));
    subContract2.setSubContractNumber("AAA111223");
    subContract2.setSubContractDate(new Date());
    contract.addSubContract(subContract2);
    contractService.create(contract);

    contract = new Contract();
    contract.setName("Продажа услуг");
    contract.setCounterparty1(counterparties.getContent().get(2));
    contract.setCounterparty2(counterparties.getContent().get(3));
    contract.setActive(true);
    contract.setContractNumber("BBBBAA22");
    contract.setStartDate(Date.from(new Date().toInstant().plus(100, DAYS)));
    contract.setEndDate(Date.from(new Date().toInstant().plus(300, DAYS)));
    contract.setType(enumerationService.getByIdentAndValue("CONTRACT-TYPE", "SERVICE"));
    contract.setStatus(enumerationService.getByIdentAndValue("CONTRACT-STATUS", "INACTIVE"));
    contractService.create(contract);

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

    // add 5 new shop specializations
    shopSpecializationtService.create(new ShopSpecialization("Специализация 1", m1, true));
    shopSpecializationtService.create(new ShopSpecialization("Специализация 2", m2, false));
    shopSpecializationtService.create(new ShopSpecialization("Специализация 3", m1, true));
    shopSpecializationtService.create(new ShopSpecialization("Специализация 4", m1, false));
    shopSpecializationtService.create(new ShopSpecialization("Специализация 5", m2, true));

    // add 5 new shop departs
    shopDepartService.create(new ShopDepart("Отдел магазин 1", m2, true));
    shopDepartService.create(new ShopDepart("Отдел магазин 2", m1, false));
    shopDepartService.create(new ShopDepart("Отдел магазин 3", m1, true));
    shopDepartService.create(new ShopDepart("Отдел магазин 4", m1, true));
    shopDepartService.create(new ShopDepart("Отдел магазин 5", m2, true));

    // add 100 new shops
    Randomizer cr = new Randomizer();
    Randomizer sc = new Randomizer();
    Randomizer st = new Randomizer();
    Randomizer sd = new Randomizer();
    Randomizer sp = new Randomizer();
    
    Page<SalesChannel> salesChannels = salesChannelService.getPage(PageRequest.of(0,10));
    Page<ShopType> shopTypes = shopTypesService.getPage(PageRequest.of(0,10));
    Page<ShopDepart> shopDeparts = shopDepartService.getPage(PageRequest.of(0,10));
    Page<ShopSpecialization> shopSpecializations = shopSpecializationtService.getPage(PageRequest.of(0,10));


    for (int i = 1; i <= 100; i++) {
      Shop shop = new Shop("Магазин" + i, counterparties.getContent().get(cr.generateRandom(9)));
      Address address = new Address("address" + i);
      LinkedHashMap<Object, Object> suggestion = new LinkedHashMap<>();
      suggestion.put(i, "s" + i);
      suggestion.put("s" + i, i);
      address.setSuggestion(suggestion);
      shop.setAddress(address);
      shop.setActive(i % 2 == 0);
      shop.addContact(c2);
      shop.addSalesChannel(salesChannels.getContent().get(sc.generateRandom(4)));
      shop.addSalesChannel(salesChannels.getContent().get(sc.generateRandom(4)));
      shop.addShopType(shopTypes.getContent().get(st.generateRandom(4)));
      shop.addShopType(shopTypes.getContent().get(st.generateRandom(4)));
      shop.addShopDepart(shopDeparts.getContent().get(sd.generateRandom(4)));
      shop.addShopDepart(shopDeparts.getContent().get(sd.generateRandom(4)));
      shop.addShopSpecialization(shopSpecializations.getContent().get(sp.generateRandom(4)));
      shop.addShopSpecialization(shopSpecializations.getContent().get(sp.generateRandom(4)));
      ExtRegSystemShopProperties shopProperties = new ExtRegSystemShopProperties();
      shopProperties.setExtRegSystem(extRegSystem);
      Map<String, Object> shopParams = new HashMap<>();
      shopParams.put("GUID", UUID.randomUUID());
      shopProperties.setProperties(shopParams);
      shop.setExtRegSystemProperties(shopProperties);
      shopService.create(shop);
    }

    //end
    appService.setInitEnd();
  }

  private void signInAsUser() {
    signInAs("user", "111111");
  }

  private void signInAsAdmin() {
    signInAs("admin", "111111");
  }

  private void signInAs(String username, String password) {
    // ***** get user - USER ******
    SecurityUser user = users.findUserByLogin(username);

    Authentication auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getLogin(), password, user.getAuthorities()));

    SecurityContext sc = SecurityContextHolder.getContext();
    sc.setAuthentication(auth);
  }

  class Randomizer {

    Random rnd = new Random();
    List<Integer> exclude = new ArrayList<>();

    int generateRandom(int end) {
      int random = 1 + rnd.nextInt(Math.max(end - exclude.size(), 1));
      for (int ex : exclude) {
        if (random < ex) {
          break;
        }
        random++;
      }
      exclude.add(random);
      return random;
    }
  }
}
