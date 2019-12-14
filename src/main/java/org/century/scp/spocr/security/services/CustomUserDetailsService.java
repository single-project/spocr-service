package org.century.scp.spocr.security.services;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import javax.servlet.http.HttpServletRequest;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.repositories.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements SecurityService, UserDetailsService {

  private final HttpServletRequest request;
  private final SecurityUserRepository users;

  @Autowired
  public CustomUserDetailsService(SecurityUserRepository users, HttpServletRequest request) {
    this.users = users;
    this.request = request;
  }

  @Override
  public int getCurrentUserId() {
    return getCurrentUser().getId();
  }

  @Override
  public String getCurrentUserLogin() {
    return getCurrentUser().getLogin();
  }

  @Override
  public SecurityUser getSecurityUser() {
    return getCurrentUser();
  }

  private SecurityUser getCurrentUser() {
    return (SecurityUser) getContext().getAuthentication().getPrincipal();
  }

  @Override
  public SecurityUser findUserById(int id) {
    return users
        .findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("Username id: " + id + " not found"));
  }

  @Override
  public SecurityUser findUserByLogin(String login) {
    return users
        .findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("Username: " + login + " not found"));
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return findUserByLogin(login);
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
