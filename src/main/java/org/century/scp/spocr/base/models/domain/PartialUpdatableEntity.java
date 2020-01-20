package org.century.scp.spocr.base.models.domain;

import java.util.List;

public interface PartialUpdatableEntity {

  List<String> getUpdatedFields();

  void setUpdatedFields(List<String> fields);
}
