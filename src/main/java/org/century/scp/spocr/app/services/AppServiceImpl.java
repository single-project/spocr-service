package org.century.scp.spocr.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.app.models.dto.SettingsView;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
public class AppServiceImpl {

  private final JdbcTemplate jdbcTemplate;

  public SettingsView getSettings() {
    try (InputStream inputStream =
        ResourceUtils.getURL("classpath:data/settings.json").openStream()) {
      return new ObjectMapper().readValue(inputStream, SettingsView.class);
    } catch (IOException e) {
      throw new SpocrException(e);
    }
  }

  public void setInitEnd() {
    String sql = "update public.app_settings set data='y' where key='DATA-INITIAL'";
    jdbcTemplate.update(sql);
  }

  public boolean wasInitialized() {
    String sql =
        "select exists(select 1from app_settings AS h where h.key='DATA-INITIAL' AND h.data='y')";
    return jdbcTemplate.queryForObject(sql, Boolean.class);
  }
}
