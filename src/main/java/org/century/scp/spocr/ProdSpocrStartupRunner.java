package org.century.scp.spocr;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.services.AccessLevelServiceImpl;
import org.century.scp.spocr.contact.services.ContactRoleService;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.enumeration.services.EnumerationService;
import org.century.scp.spocr.extlink.models.EntityType;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
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
@Profile({"prod1"})
public class ProdSpocrStartupRunner implements ApplicationRunner {

  @Autowired
  private AccessLevelServiceImpl accessLevelService;
  @Autowired
  private CustomUserDetailsService userDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private CustomUserDetailsService users;
  @Autowired
  private EnumerationService enumerationService;
  @Autowired
  private ContactRoleService contactRoleService;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    signInAsUser();

    // add 2 new cp statuses
    Enumeration enumCpStatusClient = new Enumeration();
    enumCpStatusClient.setIdent("CP-STATUS");
    enumCpStatusClient.setValue("CLIENT");
    enumCpStatusClient.buildDescriptionKey();
    enumerationService.create(enumCpStatusClient);

    Enumeration enumCpStatusProvider = new Enumeration();
    enumCpStatusProvider.setIdent("CP-STATUS");
    enumCpStatusProvider.setValue("PROVIDER");
    enumCpStatusProvider.buildDescriptionKey();
    enumerationService.create(enumCpStatusProvider);

    // add 2 new payment types
    Enumeration enumPaymentTypeCash = new Enumeration();
    enumPaymentTypeCash.setIdent("PAYMENT-TYPE");
    enumPaymentTypeCash.setValue("CASH");
    enumPaymentTypeCash.buildDescriptionKey();
    enumerationService.create(enumPaymentTypeCash);

    Enumeration enumPaymentTypeCashless = new Enumeration();
    enumPaymentTypeCashless.setIdent("PAYMENT-TYPE");
    enumPaymentTypeCashless.setValue("CASHLESS");
    enumPaymentTypeCashless.buildDescriptionKey();
    enumerationService.create(enumPaymentTypeCashless);

    // legal types
    Enumeration enumLegalTypePhys = new Enumeration();
    enumLegalTypePhys.setIdent("LEGAL-TYPE");
    enumLegalTypePhys.setValue("PHYSICAL-PERSON");
    enumLegalTypePhys.buildDescriptionKey();
    enumerationService.create(enumLegalTypePhys);

    Enumeration enumLegalTypeLegal = new Enumeration();
    enumLegalTypeLegal.setIdent("LEGAL-TYPE");
    enumLegalTypeLegal.setValue("LEGAL");
    enumLegalTypeLegal.buildDescriptionKey();
    enumerationService.create(enumLegalTypeLegal);

    // citizenship
    Enumeration enumCitizenRU = new Enumeration();
    enumCitizenRU.setIdent("CITIZENSHIP");
    enumCitizenRU.setValue("RU");
    enumCitizenRU.buildDescriptionKey();
    enumerationService.create(enumCitizenRU);

    // gender
    Enumeration enumGenderMale = new Enumeration();
    enumGenderMale.setIdent("GENDER");
    enumGenderMale.setValue("MALE");
    enumGenderMale.buildDescriptionKey();
    enumerationService.create(enumGenderMale);

    Enumeration enumGenderFemale = new Enumeration();
    enumGenderFemale.setIdent("GENDER");
    enumGenderFemale.setValue("FEMALE");
    enumGenderFemale.buildDescriptionKey();
    enumerationService.create(enumGenderFemale);

    // doc type
    Enumeration enumDocTypePass = new Enumeration();
    enumDocTypePass.setIdent("DOC-TYPE");
    enumDocTypePass.setValue("PASSPORT-RF");
    enumDocTypePass.buildDescriptionKey();
    enumerationService.create(enumDocTypePass);
  }

  public void signInAsUser() {
    // ***** add system role - ADMIN ******
    SystemRole adminRole = accessLevelService.createRole("ROLE_ADMIN");

    // ***** add system role - MANAGER ******
    SystemRole managerRole = accessLevelService.createRole("ROLE_MANAGER");

    // add possible rules
    SystemRule canReadRule = accessLevelService.createRule("READ_PRIVILEGE", EntityType.SHOP);
    SystemRule canCreateRule =
        accessLevelService.createRule("CREATE_PRIVILEGE", EntityType.SHOP_TYPE);
    SystemRule canUpdateRule =
        accessLevelService.createRule("UPDATE_PRIVILEGE", EntityType.SHOP_TYPE);

    // link role to rules
    accessLevelService.addRuleToRole(managerRole.getId(), canReadRule.getId());
    accessLevelService.addRuleToRole(managerRole.getId(), canCreateRule.getId());
    accessLevelService.addRuleToRole(managerRole.getId(), canUpdateRule.getId());

    // link user to role
    userDetailsService.addRole(1, adminRole, managerRole);

    // ***** add system role - READER ******
    managerRole = accessLevelService.createRole("ROLE_READER");

    // link role to rules
    accessLevelService.addRuleToRole(managerRole.getId(), canReadRule.getId());

    // link user to role
    userDetailsService.addRole(2, managerRole);

    SecurityUser user = users.findUserByLogin("user");
    Authentication auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getLogin(), "111111", user.getAuthorities()));

    SecurityContext sc = SecurityContextHolder.getContext();
    sc.setAuthentication(auth);
  }
}
