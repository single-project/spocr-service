package org.century.scp.spocr.events.controllers;

import java.util.List;
import java.util.Map;
import org.century.scp.spocr.events.repositories.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/events")
public class EventController {

  private EventRepositoryImpl eventRepository;

  @Autowired
  public EventController(EventRepositoryImpl eventRepository) {
    this.eventRepository = eventRepository;
  }

  @GetMapping
  public ResponseEntity<List<Map<String, Object>>> getItems() {
    return ResponseEntity.ok(eventRepository.findAll());
  }
}
