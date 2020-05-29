package org.century.scp.spocr.base.utils;

import org.apache.commons.lang3.ArrayUtils;

public class StringUtils {

  public static int getIndexIfContainsAny(final CharSequence cs, final char... searchChars) {
    if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
      return -1;
    }
    final int csLength = cs.length();
    final int searchLength = searchChars.length;
    final int csLast = csLength - 1;
    final int searchLast = searchLength - 1;
    for (int i = 0; i < csLength; i++) {
      final char ch = cs.charAt(i);
      for (int j = 0; j < searchLength; j++) {
        if (searchChars[j] == ch) {
          if (Character.isHighSurrogate(ch)) {
            if (j == searchLast) {
              // missing low surrogate, fine, like String.indexOf(String)
              return i;
            }
            if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
              return i;
            }
          } else {
            // ch is in the Basic Multilingual Plane
            return i;
          }
        }
      }
    }
    return -1;
  }

  public static boolean isEmpty(CharSequence cs) {
    return cs == null || cs.length() == 0;
  }
}
