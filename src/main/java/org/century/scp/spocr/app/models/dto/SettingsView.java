package org.century.scp.spocr.app.models.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SettingsView {

  private int defaultDataType;

  private List<TableConfig> availableTables;

  private Map<String, String> daDataConfiguration;

  private List<Map<String, String>> newLegalTypes;
}
