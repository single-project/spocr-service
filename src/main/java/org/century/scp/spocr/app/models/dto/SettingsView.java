package org.century.scp.spocr.app.models.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SettingsView {

  private int defaultDataType;

  private List<TableConfig> availableTables;
}
