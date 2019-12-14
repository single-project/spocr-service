package org.century.scp.spocr.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.ErrorResponseComposer;
import org.century.scp.spocr.exceptions.models.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandlerControllerAdvice<T extends Throwable> {

  private ErrorResponseComposer<T> errorResponseComposer;

  @Autowired
  public DefaultExceptionHandlerControllerAdvice(ErrorResponseComposer<T> errorResponseComposer) {
    this.errorResponseComposer = errorResponseComposer;
  }

  @RequestMapping(produces = "application/json")
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<?> handleException(T ex) throws T {

    ErrorResponse errorResponse = errorResponseComposer.compose(ex).orElseThrow(() -> ex);

    if (errorResponse.incomplete()) {
      throw ex;
    }

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
  }
}
