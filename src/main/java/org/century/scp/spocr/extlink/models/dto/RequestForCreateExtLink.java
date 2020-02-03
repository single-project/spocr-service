package org.century.scp.spocr.extlink.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.century.scp.spocr.extlink.models.EntityType;

public class RequestForCreateExtLink extends ExtLinkView {

  @Null
  @Override
  public Long getId() {
    return super.getId();
  }

  @NotNull
  @Override
  public int getEntityId() {
    return super.getEntityId();
  }

  @NotNull
  @Override
  public int getEntityExtId() {
    return super.getEntityExtId();
  }

  @NotNull
  @Override
  public EntityType getEntityType() {
    return super.getEntityType();
  }

  @NotNull
  @Override
  public int getExtProgId() {
    return super.getExtProgId();
  }
}
