package org.century.scp.spocr.exceptions.models;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseField {
  private String code;
  private String field;
  private String message;

  public static List<ErrorResponseField> getErrors(
      Set<ConstraintViolation<?>> constraintViolations) {

    return constraintViolations.stream().map(ErrorResponseField::of).collect(Collectors.toList());
  }

  private static ErrorResponseField of(ConstraintViolation<?> constraintViolation) {
    String field =
        StringUtils.substringAfter(constraintViolation.getPropertyPath().toString(), ".");

    return new ErrorResponseField(
        field, constraintViolation.getMessageTemplate(), constraintViolation.getMessage());
  }

  public static List<ErrorResponseField> getErrors(WebExchangeBindException ex) {

    List<ErrorResponseField> errors =
        ex.getFieldErrors().stream().map(ErrorResponseField::of).collect(Collectors.toList());

    errors.addAll(
        ex.getGlobalErrors().stream().map(ErrorResponseField::of).collect(Collectors.toSet()));

    return errors;
  }

  private static ErrorResponseField of(FieldError fieldError) {

    return new ErrorResponseField(
        fieldError.getObjectName() + "." + fieldError.getField(),
        fieldError.getCode(),
        fieldError.getDefaultMessage());
  }

  public static ErrorResponseField of(ObjectError error) {

    return new ErrorResponseField(
        error.getObjectName(), error.getCode(), error.getDefaultMessage());
  }
}
