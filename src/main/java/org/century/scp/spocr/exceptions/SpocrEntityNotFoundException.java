package org.century.scp.spocr.exceptions;

import org.century.scp.spocr.base.utils.EntityNameConverter;

public class SpocrEntityNotFoundException extends SpocrException {

  public SpocrEntityNotFoundException(Class<?> c, long id) {
    super(String.format("%s-not-found.exception", EntityNameConverter.toMessageServiceKey(c)),
        id);
  }

}
