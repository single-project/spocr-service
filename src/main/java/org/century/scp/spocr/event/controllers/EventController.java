package org.century.scp.spocr.event.controllers;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.century.scp.spocr.base.utils.StringUtils;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

  private final EventRepositoryImpl eventRepository;

  @GetMapping
  public ResponseEntity<List<Map<String, Object>>> getItems(
      @RequestParam(value = "q", required = false, defaultValue = "id>0") String q) {
    return ResponseEntity.ok(doQuery(q));
  }

  @GetMapping("/maxId")
  public ResponseEntity<Long> getItems() {
    return ResponseEntity.ok(eventRepository.getMaxId());
  }

  // TODO: перейти к чему то вменяемому, если кол-во вариантов вырастет больше 10
  public List<Map<String, Object>> doQuery(String query) {
    if (StringUtils.isEmpty(query)) {
      throw new SpocrException("Некорректный запрос");
    }

    char[] operations = {'>'};
    try {
      // binary operation
      int index = StringUtils.getIndexIfContainsAny(query, operations);
      if (index > -1) {
        char op = query.charAt(index);

        return eventRepository.findAll(
            query.substring(0, index + 1) + "?", Integer.valueOf((query.substring(index + 1))));
      } else {
        throw new SpocrException("Некорректный запрос");
      }
    } catch (NumberFormatException e) {
      throw new SpocrException("Некорректный запрос");
    }
  }
}
