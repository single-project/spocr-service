package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuntimeExceptionHandler extends AbstractExceptionHandler<RuntimeException> {

  public RuntimeExceptionHandler() {
    super(RuntimeException.class);
  }

}
