package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuntimeExceptionHandler extends AbstractExceptionHandler<Throwable> {

  public RuntimeExceptionHandler() {
    super(Throwable.class);
  }

}
