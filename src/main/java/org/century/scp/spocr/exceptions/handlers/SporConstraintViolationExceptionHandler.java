package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.exceptions.SpocrConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SporConstraintViolationExceptionHandler extends
    AbstractSpocrExceptionHandler<SpocrConstraintViolationException> {

  public SporConstraintViolationExceptionHandler(DefaultMessageSource messageSource) {
    super(SpocrConstraintViolationException.class, messageSource);
  }

  @Override
  public HttpStatus getStatus(SpocrConstraintViolationException ex) {
    return HttpStatus.CONFLICT;
  }
}
