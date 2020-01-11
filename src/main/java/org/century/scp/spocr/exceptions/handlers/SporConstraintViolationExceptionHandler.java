package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.SpocrConstraintViolationException;
import org.century.scp.spocr.exceptions.SpocrEntityNotFoundException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SporConstraintViolationExceptionHandler<E extends SpocrException>
    extends AbstractExceptionHandler<E> {

  public SporConstraintViolationExceptionHandler() {
    super(SpocrConstraintViolationException.class);
  }

  @Override
  public HttpStatus getStatus(E ex) {
    return HttpStatus.CONFLICT;
  }
}
