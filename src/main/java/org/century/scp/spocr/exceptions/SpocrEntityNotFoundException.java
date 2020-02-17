package org.century.scp.spocr.exceptions;

import com.google.common.base.CaseFormat;

public class SpocrEntityNotFoundException extends SpocrException {

  public SpocrEntityNotFoundException(Class<?> c, long id) {
    super(String.format("%s-not-found.exception",
        CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(c.getSimpleName())),
        id);
  }

  public SpocrEntityNotFoundException(String message) {
    super(message);
  }

}
