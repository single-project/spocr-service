package org.century.scp.spocr.event.repositories;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.converters.MapConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventRepositoryImpl {

  private final JdbcTemplate jdbcTemplate;
  private MapConverter mapConverter = new MapConverter();

  public Long getMaxId() {
    String sql = "SELECT h.id" + " FROM public.events AS h" + " ORDER BY h.id DESC" + " LIMIT 1";
    Long maxId = jdbcTemplate.queryForObject(sql, Long.class);
    return maxId == null ? 0 : maxId;
  }

  public List<Map<String, Object>> findAll(String query, Object... params) {
    String sql =
        "SELECT h.id, h.ident, h.entity, h.ts, h.body, h.username"
            + " FROM public.events AS h"
            + " WHERE h."
            + query
            + " ORDER BY h.id";
    return jdbcTemplate.queryForList(sql, params);
  }

  public void insert(String ident, String entity, Map<String, Object> body, String username) {
    String sql =
        "INSERT INTO public.events " + "(ident, entity, ts, body, username) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(
        sql,
        ps -> {
          ps.setString(1, ident);
          ps.setString(2, entity);
          ps.setTimestamp(3, new Timestamp(new Date().getTime()));
          ps.setString(4, mapConverter.convertToDatabaseColumn(body));
          ps.setString(5, username);
        });
  }
}
