package org.century.scp.spocr.address.models.dto;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class AddressView extends BaseEntityView {

  private String address;
  private String comment;
  private HashMap suggestion;
  private Double latitude;
  private Double longitude;

  public AddressView(String address, HashMap suggestion) {
    this.address = address;
    this.suggestion = suggestion;
  }
}
