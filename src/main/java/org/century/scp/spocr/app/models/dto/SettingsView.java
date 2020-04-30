package org.century.scp.spocr.app.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.utils.CustomBeanUtils;

@Data
@NoArgsConstructor
public class SettingsView {

  private Integer defaultDataType;

  private List<TableConfig> availableTables;

  private Map<String, String> daDataConfiguration;

  private Map<String, Object> dateTimeConfig;

  private Map<String, Object> calendarConfiguration;

  // TODO: refactor it: user+app settings
  public void override(SettingsView settings) {
    List<String> properties = new ArrayList<>();
    properties.add("defaultDataType");
    properties.add("availableTables");
    properties.add("daDataConfiguration");
    properties.add("dateTimeConfig");
    properties.add("calendarConfiguration");
    CustomBeanUtils.copyProperties(settings, this, properties);
  }

  public void setDefaultDataType(Integer defaultDataType) {
    if (defaultDataType != null) {
      this.defaultDataType = defaultDataType;
    }
  }

  public void setAvailableTables(List<TableConfig> availableTables) {
    if (availableTables != null) {
      this.availableTables = availableTables;
    }
  }

  public void setDaDataConfiguration(Map<String, String> daDataConfiguration) {
    if (daDataConfiguration != null) {
      this.daDataConfiguration = daDataConfiguration;
    }
  }

  public void setDateTimeConfig(Map<String, Object> dateTimeConfig) {
    if (dateTimeConfig != null) {
      this.dateTimeConfig = dateTimeConfig;
    }
  }

  public void setCalendarConfiguration(Map<String, Object> calendarConfiguration) {
    if (calendarConfiguration != null) {
      this.calendarConfiguration = calendarConfiguration;
    }
  }
}
