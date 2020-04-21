package org.century.scp.spocr.exceptions;

public class SpocrConstraintViolationException extends SpocrException {

  public SpocrConstraintViolationException(String messageFormatKey, Object... arguments) {
    super(messageFormatKey, arguments);
  }
}
