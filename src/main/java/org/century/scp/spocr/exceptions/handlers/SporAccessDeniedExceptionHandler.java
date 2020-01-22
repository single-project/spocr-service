package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.SpocrConstraintViolationException;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SporAccessDeniedExceptionHandler extends AbstractExceptionHandler<AccessDeniedException> {

  public SporAccessDeniedExceptionHandler() {
    super(AccessDeniedException.class);
  }

  @Override
  public HttpStatus getStatus(AccessDeniedException ex) {
    return HttpStatus.FORBIDDEN;
  }
}
