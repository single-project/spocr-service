package org.century.scp.spocr.shoptype.models.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.manufacturer.models.dto.ManufacturerView;

@Getter
@Setter
@NoArgsConstructor
public class RequestForUpdateShopType extends ShopTypeView {

  private List<String> updatedFields;

  @NotNull
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

  @NotNull
  @NotEmpty
  @Override
  public String getName() {
    return super.getName();
  }

  @NotNull
  @Override
  public ManufacturerView getManufacturer() {
    return super.getManufacturer();
  }

  @NotNull
  @Size(min = 1)
  public List<String> getUpdatedFields() {
    return this.updatedFields;
  }
}
