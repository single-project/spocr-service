package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SporExceptionHandler extends AbstractSpocrExceptionHandler<SpocrException> {

  public SporExceptionHandler(DefaultMessageSource messageSource) {
    super(SpocrException.class, messageSource);
  }

  @Override
  public HttpStatus getStatus(SpocrException ex) {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

}
