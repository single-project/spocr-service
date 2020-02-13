package org.century.scp.spocr.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.app.models.dto.SettingsView;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
public class AppServiceImpl {

  public SettingsView getSettings() {
    try {
      File file = ResourceUtils.getFile("classpath:data/settings.json");
      return new ObjectMapper().readValue(file, SettingsView.class);
    } catch (IOException e) {
      throw new SpocrException(e);
    }
  }
}
