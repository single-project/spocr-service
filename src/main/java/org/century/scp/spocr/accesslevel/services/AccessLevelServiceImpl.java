package org.century.scp.spocr.accesslevel.services;

import java.util.List;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.accesslevel.repositories.SystemRoleRepository;
import org.century.scp.spocr.accesslevel.repositories.SystemRuleRepository;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.extlink.models.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLevelServiceImpl {

  private SystemRuleRepository ruleRepository;
  private SystemRoleRepository roleRepository;

  @Autowired
  public AccessLevelServiceImpl(
      SystemRuleRepository ruleRepository, SystemRoleRepository roleRepository) {
    this.ruleRepository = ruleRepository;
    this.roleRepository = roleRepository;
  }

  public List<SystemRole> getRoles() {
    return roleRepository.findAll();
  }

  public List<SystemRole> getRules() {
    return roleRepository.findAll();
  }

  public SystemRole createRole(String name) {
    return roleRepository.save(new SystemRole(name));
  }

  public SystemRole updateRole(long id, String name) {
    SystemRole role = getRole(id);
    role.setName(name);
    return roleRepository.save(role);
  }

  public SystemRule createRule(String name, EntityType entityType) {
    return ruleRepository.save(new SystemRule(name));
  }

  public SystemRule updateRule(long id, String name, EntityType entityType) {
    SystemRule rule = getRule(id);
    rule.setName(name);
    return ruleRepository.save(rule);
  }

  public SystemRole addRuleToRole(long roleId, long ruleId) {
    SystemRole role = getRole(roleId);
    SystemRule rule = getRule(ruleId);
    role.addRule(rule);
    return roleRepository.save(role);
  }

  private SystemRule getRule(long id) {
    return ruleRepository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(SystemRole.class, id));
  }

  private SystemRole getRole(long id) {
    return roleRepository
        .findById(id)
        .orElseThrow(() -> new SpocrEntityNotFoundException(SystemRole.class, id));
  }
}
