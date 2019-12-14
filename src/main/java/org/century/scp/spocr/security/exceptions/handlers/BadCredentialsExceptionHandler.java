package org.century.scp.spocr.security.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.handlers.AbstractExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class BadCredentialsExceptionHandler<E extends BadCredentialsException>
    extends AbstractExceptionHandler<E> {

  public BadCredentialsExceptionHandler() {
    super(BadCredentialsException.class);
  }

  @Override
  public HttpStatus getStatus(E ex) {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public String getMessage(E ex) {
    return "Неправильно указаны логин или пароль";
  }
}
