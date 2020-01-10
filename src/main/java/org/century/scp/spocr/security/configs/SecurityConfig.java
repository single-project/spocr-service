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
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**", "/actuator/health", "/actuator/mappings");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/auth/signin")
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
