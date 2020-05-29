package org.century.scp.spocr.app.controllers;

import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.app.models.dto.RequestForCreateUpdateSettingsView;
import org.century.scp.spocr.app.models.dto.SettingsView;
import org.century.scp.spocr.app.services.AppServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

  private final AppServiceImpl appService;

  @GetMapping()
  public String getAllEndpoints() {
    return "redirect:/actuator/mappings";
  }

  @GetMapping("/app/settings")
  public ResponseEntity<SettingsView> getAppSettings() {
    return ResponseEntity.ok(appService.getSettings());
  }

  @PostMapping("/user/settings")
  public ResponseEntity createUpdateUserSettings(@Validated @RequestBody RequestForCreateUpdateSettingsView settings) {
    return ResponseEntity.ok(appService.updateUserSettings(settings));
  }


  @GetMapping("/user/settings")
  public ResponseEntity<SettingsView> getUserSettings() {
    return ResponseEntity.ok(appService.getSettings());
  }
}
