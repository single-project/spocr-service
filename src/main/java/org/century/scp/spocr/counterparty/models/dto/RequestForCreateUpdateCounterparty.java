package org.century.scp.spocr.counterparty.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.validators.OnCreate;
import org.century.scp.spocr.base.validators.OnUpdate;

@Getter
@Setter
@NoArgsConstructor
public class RequestForCreateUpdateCounterparty extends CounterpartyView {

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  @Override
  public Long getId() {
    return super.getId();
  }

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  @PositiveOrZero(groups = OnUpdate.class)
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull(groups = {OnCreate.class, OnUpdate.class})
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @NotNull(groups = {OnCreate.class, OnUpdate.class})
  @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
  @Override
  public String getName() {
    return super.getName();
  }

  @JsonIgnore(false)
  @NotNull(groups = OnUpdate.class)
  @Size(min = 1)
  @Override
  public List<String> getUpdatedFields() {
    return super.getUpdatedFields();
  }
}
