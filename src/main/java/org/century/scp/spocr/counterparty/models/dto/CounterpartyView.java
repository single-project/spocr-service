package org.century.scp.spocr.counterparty.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;

@Getter
@Setter
@NoArgsConstructor
public class CounterpartyView extends BaseEntityView {

  private String name;

  public CounterpartyView(String name) {
    this.name = name;
  }

  public CounterpartyView(Long id, String name, Long version, boolean active) {
    super(id, version, active);
    this.name = name;
  }

}
