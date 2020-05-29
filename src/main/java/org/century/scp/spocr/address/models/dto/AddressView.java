package org.century.scp.spocr.address.models.dto;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressView {

  private Long id;
  private String address;
  private String comment;
  private Map suggestion;
  private Double latitude;
  private Double longitude;
  private Long version;

}
