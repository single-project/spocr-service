package org.century.scp.spocr.exceptions.handlers;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.models.ErrorResponse;
import org.century.scp.spocr.exceptions.models.ErrorResponseField;
import org.springframework.http.HttpStatus;

@Slf4j
public abstract class AbstractExceptionHandler<T extends Throwable> {

  private Class<?> exceptionClass;

  public AbstractExceptionHandler(Class<T> exceptionClass) {
    this.exceptionClass = exceptionClass;
  }

  public Class<?> getExceptionClass() {
    return exceptionClass;
  }

  protected String getMessage(T ex) {
    return ex.getMessage();
  }

  protected HttpStatus getStatus(T ex) {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  private List<ErrorResponseField> getErrors(T ex) {
    return Collections.emptyList();
  }

  public ErrorResponse getErrorResponse(T ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(getMessage(ex));

    HttpStatus status = getStatus(ex);
    errorResponse.setStatus(status.value());
    errorResponse.setError(status.getReasonPhrase());

    errorResponse.setErrors(getErrors(ex));
    return errorResponse;
  }

}
