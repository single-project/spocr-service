package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CommonExceptionHandler extends AbstractExceptionHandler<Throwable> {

  public CommonExceptionHandler() {
    super(Throwable.class);
  }

}
