package org.century.scp.spocr.app.models.dto;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestForCreateUpdateSettingsView extends SettingsView {

  @Null
  @Override
  public List<TableConfig> getAvailableTables() {
    return super.getAvailableTables();
  }

  @Null
  @Override
  public Map<String, String> getDaDataConfiguration() {
    return super.getDaDataConfiguration();
  }

  @NotNull
  @Override
  public Map<String, Object> getDateTimeConfig() {
    return super.getDateTimeConfig();
  }

  @Null
  @Override
  public Map<String, Object> getCalendarConfiguration() {
    return super.getCalendarConfiguration();
  }
}
