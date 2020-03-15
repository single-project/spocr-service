package org.century.scp.spocr.base.utils;

import com.google.common.base.CaseFormat;

public class EntityNameConverter {

  public static String toMetaDataKey(Class<?> c) {
    return CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE)
        .convert(c.getSimpleName());
  }

  public static String toMessageServiceKey(Class<?> c) {
    return CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(c.getSimpleName());
  }

}
