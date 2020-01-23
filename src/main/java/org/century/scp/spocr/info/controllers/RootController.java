package org.century.scp.spocr.info.controllers;

import org.century.scp.spocr.info.models.dto.SettingsView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RootController {

  @GetMapping()
  public String getAllEndpoints() {
    return "redirect:/actuator/mappings";
  }

  @GetMapping("/app/settings")
  public ResponseEntity<SettingsView> getAppSettings() {
    return ResponseEntity.ok(new SettingsView());
  }

  @GetMapping("/user/settings")
  public ResponseEntity<SettingsView> geUserSettings() {
    return ResponseEntity.ok(new SettingsView());
  }
}
