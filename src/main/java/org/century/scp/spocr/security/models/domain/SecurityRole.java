package org.century.scp.spocr.security.models.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Builder
@Table(name = "security_user_role_table", schema = "public")
@NoArgsConstructor
public class SecurityRole implements GrantedAuthority, Serializable {

  @Id
  @Column(name = "login", nullable = false)
  private String login;

  @Id
  @Column(name = "role_name", nullable = false)
  private String role;

  public SecurityRole(String login, String role) {
    this.login = login;
    this.role = role;
  }

  @Override
  public String toString() {
    return "SecurityRole{" + "login='" + login + '\'' + ", role='" + role + '\'' + '}';
  }

  @Override
  public String getAuthority() {
    return login;
  }
}
