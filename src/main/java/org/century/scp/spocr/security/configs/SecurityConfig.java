package org.century.scp.spocr.security.configs;

import org.century.scp.spocr.security.services.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@ServletComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtTokenProvider jwtTokenProvider;

  @Autowired private AuthenticationEntryPoint authenticationEntryPoint;

  private static final String[] AUTH_WHITELIST = {
      // -- auth
      "/auth/signin",
      // -- h2
      "/h2-console/**",
      // -- actuator
      "/actuator/health",
      "/actuator/mappings",
      // -- swagger ui
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
  };

  @Bean
  @ConditionalOnMissingBean(RequestContextListener.class)
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder md5PasswordEncoder() {
    return new MessageDigestPasswordEncoder("MD5");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider))
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint);

    logoutDisabledPolicy(http);
    statelessSessionPolicy(http);
  }

  protected void logoutDisabledPolicy(HttpSecurity http) throws Exception {
    http.logout().disable();
  }

  protected void statelessSessionPolicy(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
