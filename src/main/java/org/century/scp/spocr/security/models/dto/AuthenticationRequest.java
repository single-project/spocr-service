package org.century.scp.spocr.security.models.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
