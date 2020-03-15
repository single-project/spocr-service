package org.century.scp.spocr.extlink.models;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EntityType {
  CONTACT,
  CONTACT_ROLE,
  CONTRACT,
  SUB_CONTRACT,
  OWNER,
  SHOP,
  SHOP_TYPE,
  SALES_CHANNEL,
  SHOP_DEPART,
  SHOP_SPECIALIZATION,
  MANUFACTURER,
  COUNTERPARTY,
  EXT_REG_SYSTEM;

  public static List<String> getTypes() {
    return Stream.of(values()).map(Enum::name).collect(Collectors.toList());
  }
}
