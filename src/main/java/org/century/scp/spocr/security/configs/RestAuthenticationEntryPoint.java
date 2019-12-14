package org.century.scp.spocr.security.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.century.scp.spocr.exceptions.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {
    ErrorResponse errorResponse;
    if (e instanceof BadCredentialsException) {
      errorResponse = ErrorResponse.builder().message(e.getMessage()).build();
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    } else {
      errorResponse =
          ErrorResponse.builder()
              .message("Для доступа к этому ресурсу требуется аутентификация")
              .status(HttpStatus.UNAUTHORIZED.value())
              .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
              .build();
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      httpServletResponse.setContentType("application/json; charset=utf-8");
    }

    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, errorResponse);
    out.flush();
  }
}
