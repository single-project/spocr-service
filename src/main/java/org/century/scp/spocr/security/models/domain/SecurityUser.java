package org.century.scp.spocr.security.models.domain;

import static org.hibernate.annotations.LazyCollectionOption.FALSE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.springframework.security.core.GrantedAuthority;
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

  @OneToMany
  @JsonIgnore
  @JoinColumn(name = "login", referencedColumnName = "login")
  @LazyCollection(FALSE)
  private List<SecurityRole> roles;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
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
