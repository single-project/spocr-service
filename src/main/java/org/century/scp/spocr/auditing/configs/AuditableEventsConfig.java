package org.century.scp.spocr.auditing.configs;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class AuditableEventsConfig {

  private DataSource dataSource;

  @Autowired
  public AuditableEventsConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public JdbcTemplate createJdbcTemplateBean() {
    return new JdbcTemplate(dataSource);
  }
}
