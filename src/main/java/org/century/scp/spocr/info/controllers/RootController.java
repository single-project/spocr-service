package org.century.scp.spocr.info.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RootController {

  @GetMapping("/{id}")
  public String getAllEndpoints() {
    return "redirect:/actuator/mappings";
  }
}
