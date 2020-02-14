package org.century.scp.spocr.app.models.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TableConfig {

  private int id;
  private String name;
  private String url;
  private List<FieldConfig> columns;
  private String sortField;
  private String sortOrder;

  public TableConfig() {
    this.columns = new ArrayList<>();
    this.sortField = "name";
    this.sortField = "asc";
  }

  public void addColumn(FieldConfig fieldConfig) {
    columns.add(fieldConfig);
  }
}
