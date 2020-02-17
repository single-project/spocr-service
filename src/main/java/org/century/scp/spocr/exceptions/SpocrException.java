package org.century.scp.spocr.exceptions;

public class SpocrException extends RuntimeException {

  private Object[] arguments;
  private String messageFormatKey;

  public SpocrException() {
    this.messageFormatKey = "something-goes-wrong.exception";
  }

  public SpocrException(String messageFormatKey, Object... arguments) {
    this.arguments = arguments;
    this.messageFormatKey = messageFormatKey;
  }

  public Object[] getArguments() {
    return arguments;
  }

  public String getMessageFormatKey() {
    return messageFormatKey;
  }

  public SpocrException(String message) {
    super(message);
  }

  public SpocrException(Throwable cause) {
    super(cause);
  }

  public SpocrException(String message, Throwable cause) {
    super(message, cause);
  }
}
