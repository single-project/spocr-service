package org.century.scp.spocr.info.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SettingsView {

  private int defaultDataType = 1;
}
