package org.century.scp.spocr.base.utils;

public class Strings {

  private static final String EMPTY = "";

  public static boolean isNullOrEmpty(String target) {
    return target == null || EMPTY.equals(target);
  }
}
