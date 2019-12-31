package org.century.scp.spocr.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpocrException extends RuntimeException {
  public SpocrException(String message) {
    super(message);
  }

  public SpocrException(Throwable cause) {
    super(cause);
  }
}
