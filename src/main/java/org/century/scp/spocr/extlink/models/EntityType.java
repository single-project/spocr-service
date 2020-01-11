package org.century.scp.spocr.extlink.models;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EntityType {
  SHOP,
  SHOP_TYPE,
  MANUFACTURER;

  public static List<String> getTypes() {
    return Stream.of(values()).map(Enum::name).collect(Collectors.toList());
  }
}
