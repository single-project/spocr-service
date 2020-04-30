package org.century.scp.spocr.app.services;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.app.models.dto.SettingsView;
import org.century.scp.spocr.exceptions.SpocrException;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl {

  private final JdbcTemplate jdbcTemplate;

  public SettingsView getSettings() {
    try {
      String sql = "select data from app_settings where key='global'";
      String jsonString = jdbcTemplate.queryForObject(sql, String.class);
      SettingsView appSettings = new ObjectMapper().readValue(jsonString, SettingsView.class);
      SettingsView userSettings = getUserSettings();
      appSettings.override(userSettings);
      return appSettings;
    } catch (IOException e) {
      log.error("failed-to-load-application-settings", e);
      throw new SpocrException("failed-to-load-application-settings");
    }
  }

  public SettingsView getUserSettings() {
    String user = getCurrentUserLogin();
    String sql = "select data from user_settings where username=?";
    String jsonString = jdbcTemplate.queryForObject(sql, String.class, user);
    try {
      return new ObjectMapper().readValue(jsonString, SettingsView.class);
    } catch (IOException e) {
      log.error("failed-to-load-user-settings", e);
      throw new SpocrException("failed-to-load-user-settings");
    }
  }

  private boolean userSettingsExists() {
    String user = getCurrentUserLogin();
    String sql = "select exists(select 1 from user_settings where username=?)";
    return jdbcTemplate.queryForObject(sql, Boolean.class, user);
  }

  public boolean createOrUpdateUserSettings(SettingsView settings) {
    if (userSettingsExists()) {
      return updateUserSettings(settings);
    } else {
      return createUserSettings(settings);
    }
  }

  public boolean createUserSettings(SettingsView settings) {
    String user = getCurrentUserLogin();
    try {
      String data = new ObjectMapper().writeValueAsString(settings);
      String sql = "insert public.user_settings (username, data) values('?', '?')";
      int insertedRows =
          jdbcTemplate.update(
              sql,
              ps -> {
                ps.setString(1, data);
                ps.setString(2, user);
              });

      return insertedRows > 0;
    } catch (JsonProcessingException e) {
      log.error("failed-to-insert-user-settings", e);
      throw new SpocrException(e);
    }
  }

  public boolean updateUserSettings(SettingsView settings) {
    String user = getCurrentUserLogin();
    try {
      String data = new ObjectMapper().writeValueAsString(settings);
      String sql = "update public.user_settings set data=? where username=?";
      int updatedRows =
          jdbcTemplate.update(
              sql,
              ps -> {
                ps.setString(1, data);
                ps.setString(2, user);
              });

      return updatedRows > 0;
    } catch (JsonProcessingException e) {
      log.error("failed-to-update-user-settings", e);
      throw new SpocrException(e);
    }
  }

  public void setInitEnd() {
    String sql = "update public.app_settings set data='y' where key='DATA-INITIAL'";
    jdbcTemplate.update(sql);
  }

  public boolean wasInitialized() {
    String sql =
        "select exists(select 1 from app_settings AS h where h.key='DATA-INITIAL' AND h.data='y')";
    return jdbcTemplate.queryForObject(sql, Boolean.class);
  }

  private String getCurrentUserLogin() {
    return ((SecurityUser) getContext().getAuthentication().getPrincipal()).getLogin();
  }
}
