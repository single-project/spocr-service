package org.century.scp.spocr.security.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roles")
@Table(name = "security_user", schema = "public")
public class SecurityUser implements UserDetails, Serializable {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "login", nullable = false)
  private String login;

  @Column(name = "passwd")
  private String password;

  @Column(name = "is_active", nullable = false)
  private boolean active;

  @Column(name = "comment")
  private String comment;

  @Column(name = "tabnum", nullable = false)
  private int tabnum;

  @Column(name = "date_create")
  private Date create;

  @Column(name = "date_update")
  private Date update;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_to_system_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "system_roles_id"))
  private List<SystemRole> roles;

  public void addRole(SystemRole role) {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    roles.add(role);
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SystemRule> rules = new ArrayList<>();
    List<GrantedAuthority> authorities = new ArrayList<>();
    roles.forEach(r -> rules.addAll(r.getSystemRules()));
    rules.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
    return authorities;
  }

  @Override
  public String getUsername() {
    return this.login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
