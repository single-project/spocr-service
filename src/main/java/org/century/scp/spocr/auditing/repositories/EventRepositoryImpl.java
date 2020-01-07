package org.century.scp.spocr.auditing.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventRepositoryImpl {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public EventRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Map<String, Object>> findAll(int q) {
    String sql =
        "SELECT h.id, h.ident, h.ts, h.body"
            + " FROM public.events AS h"
            + " WHERE h.id > ?"
            + " ORDER BY h.id";
    return jdbcTemplate.queryForList(sql, q);
  }

  public void insert(String ident, Map<String, Object> body) {
    String sql = "INSERT INTO public.events " + "(ident, ts, body) VALUES (?, ?, ?)";
    jdbcTemplate.update(
        sql,
        ps -> {
          ps.setString(1, ident);
          ps.setTimestamp(2, new Timestamp(new Date().getTime()));
          ps.setString(3, mapToJsonString(body));
        });
  }

  private String mapToJsonString(Map<String, Object> body) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(body);
    } catch (JsonProcessingException e) {
      throw new SpocrException("Не удалось преобразовать данные", e);
    }
  }
}
