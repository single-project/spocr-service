package org.century.scp.spocr.security.services;

import org.century.scp.spocr.security.models.domain.SecurityUser;

public interface SecurityService {

  int getCurrentUserId();

  String getCurrentUserLogin();

  SecurityUser getSecurityUser();

  SecurityUser findUserById(int id);

  SecurityUser findUserByLogin(String login);
}
