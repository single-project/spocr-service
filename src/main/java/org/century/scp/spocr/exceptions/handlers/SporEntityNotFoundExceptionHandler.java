package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SporEntityNotFoundExceptionHandler extends
    AbstractSpocrExceptionHandler<SpocrEntityNotFoundException> {


  public SporEntityNotFoundExceptionHandler(DefaultMessageSource messageSource) {
    super(SpocrEntityNotFoundException.class, messageSource);
  }

  @Override
  public HttpStatus getStatus(SpocrEntityNotFoundException ex) {
    return HttpStatus.NOT_FOUND;
  }
}
