package org.century.scp.spocr.exceptions.handlers;

import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.exceptions.SpocrException;

public abstract class AbstractSpocrExceptionHandler<T extends SpocrException>
    extends AbstractExceptionHandler<T> {

  private DefaultMessageSource messageSource;

  AbstractSpocrExceptionHandler(Class<T> exceptionClass, DefaultMessageSource messageSource) {
    super(exceptionClass);
    this.messageSource = messageSource;
  }

  @Override
  public String getMessage(T ex) {
    String message = ex.getMessage();
    if (ex.getMessageFormatKey() != null) {
      message = messageSource.getMessage(ex.getMessageFormatKey(), ex.getArguments());
    }
    return message;
  }
}
