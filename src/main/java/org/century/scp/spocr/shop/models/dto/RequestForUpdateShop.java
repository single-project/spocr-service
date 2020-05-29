package org.century.scp.spocr.shop.models.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateShop extends ShopView {

  private List<String> updatedFields;

  @Positive
  @Override
  public Long getId() {
    return super.getId();
  }

  @PositiveOrZero
  @Override
  public Long getVersion() {
    return super.getVersion();
  }

  @NotNull
  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @NotBlank
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public BaseEntityListView getCounterparty() {
    return super.getCounterparty();
  }

  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return updatedFields;
  }
}
