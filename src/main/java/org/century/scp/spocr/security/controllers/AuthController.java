package org.century.scp.spocr.security.controllers;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.century.scp.spocr.security.models.dto.AuthenticationRequest;
import org.century.scp.spocr.security.models.dto.AuthenticationResponse;
import org.century.scp.spocr.security.services.CustomUserDetailsService;
import org.century.scp.spocr.security.services.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Value("${security.jwt.token.expire-length}")
  private String tokenTTL;

  private CustomUserDetailsService users;
  private JwtTokenProvider jwtTokenProvider;
  private AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(
      CustomUserDetailsService users,
      JwtTokenProvider jwtTokenProvider,
      AuthenticationManager authenticationManager) {
    this.users = users;
    this.jwtTokenProvider = jwtTokenProvider;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/signin")
  public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest data) {
    String username = data.getUsername();
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, data.getPassword()));
    SecurityUser user = users.findUserByLogin(username);
    String token = jwtTokenProvider.createToken(username, user.getRoles());
    return ok(
        AuthenticationResponse.builder()
            .username(username)
            .token(token)
            .tokenTTL(tokenTTL)
            .build());
  }

  @GetMapping("/me")
  public ResponseEntity getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
    Map<Object, Object> model = new HashMap<>();
    model.put("username", userDetails.getUsername());
    model.put(
        "roles",
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(toList()));
    return ok(model);
  }
}
