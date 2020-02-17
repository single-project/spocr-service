package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class BadCredentialsExceptionHandler extends
    AbstractExceptionHandler<BadCredentialsException> {

  private DefaultMessageSource messageSource;

  public BadCredentialsExceptionHandler(DefaultMessageSource messageSource) {
    super(BadCredentialsException.class);
    this.messageSource = messageSource;
  }

  @Override
  public HttpStatus getStatus(BadCredentialsException ex) {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public String getMessage(BadCredentialsException ex) {
    return messageSource.getMessage("incorrect-credentials.exception");
  }
}
