package org.century.scp.spocr.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuntimeExceptionHandler extends AbstractExceptionHandler<RuntimeException> {

  public RuntimeExceptionHandler(DefaultMessageSource messageSource) {
    super(RuntimeException.class);
  }

}
