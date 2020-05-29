package org.century.scp.spocr.draft.models.dto;

import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RequestForUpdateDraft extends DraftView {

  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

  @Override
  @NotBlank
  public String getEntity() {
    return super.getEntity();
  }

  @Override
  @NotBlank
  public Map<String, String> getBody() {
    return super.getBody();
  }
}
