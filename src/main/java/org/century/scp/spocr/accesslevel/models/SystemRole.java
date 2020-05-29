package org.century.scp.spocr.accesslevel.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "system_roles")
@NoArgsConstructor
@AllArgsConstructor
public class SystemRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private Boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_to_rules",
      joinColumns = @JoinColumn(name = "system_role_id"),
      inverseJoinColumns = @JoinColumn(name = "system_rules_id"))
  List<SystemRule> systemRules;

  public SystemRole(String name) {
    this.name = name;
    this.active = true;
  }

  public void addRule(SystemRule rule) {
    if (systemRules == null) {
      systemRules = new ArrayList<>();
    }

    systemRules.add(rule);
  }

  public void replaceRules(List<SystemRule> rules) {
    if (this.systemRules == null) {
      this.systemRules = new ArrayList<>();
    } else {
      this.systemRules.clear();
    }
    systemRules.addAll(rules);
  }
}
