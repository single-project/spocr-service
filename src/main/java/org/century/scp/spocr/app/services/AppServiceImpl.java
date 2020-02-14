package org.century.scp.spocr.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.app.models.dto.SettingsView;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
public class AppServiceImpl {

  public SettingsView getSettings() {
    try (InputStream inputStream =
        ResourceUtils.getURL("classpath:data/settings.json").openStream()) {
      return new ObjectMapper().readValue(inputStream, SettingsView.class);
    } catch (IOException e) {
      throw new SpocrException(e);
    }
  }
}
