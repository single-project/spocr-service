package org.century.scp.spocr.exceptions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.exceptions.handlers.AbstractExceptionHandler;
import org.century.scp.spocr.exceptions.models.ErrorResponse;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorResponseComposer<T extends Throwable> {

  private final Map<Class<?>, AbstractExceptionHandler<T>> handlers;

  public ErrorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {

    this.handlers =
        handlers.stream()
            .collect(
                Collectors.toMap(
                    AbstractExceptionHandler::getExceptionClass,
                    Function.identity(),
                    (handler1, handler2) ->
                        AnnotationAwareOrderComparator.INSTANCE.compare(handler1, handler2) < 0
                            ? handler1
                            : handler2));
  }

  public Optional<ErrorResponse> compose(T ex) {

    AbstractExceptionHandler<T> handler = null;

    while (ex != null) {
      handler = handlers.get(ex.getClass());
      if (handler != null) break;
      ex = (T) ex.getCause();
    }

    if (handler != null) {
      return Optional.of(handler.getErrorResponse(ex));
    }

    return Optional.empty();
  }

  private Optional<AbstractExceptionHandler<T>> findHandler(T ex) {
    AbstractExceptionHandler<T> handler = null;
    while ((ex = (T) ex.getCause()) != null && (handler = handlers.get(ex.getClass())) != null) {
      //
    }

    return Optional.of(handler);
  }
}
