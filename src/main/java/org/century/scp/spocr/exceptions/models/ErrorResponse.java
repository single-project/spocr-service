package org.century.scp.spocr.exceptions.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  private Integer status;
  private String error;
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ErrorResponseField> errors;

  public boolean incomplete() {
    return message == null || status == null;
  }
}
