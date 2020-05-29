package org.century.scp.spocr.base.configs;

import java.util.Locale;
import org.century.scp.spocr.base.i18.DefaultMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class I18Config {

  @Bean
  public MessageSource messageSource() {
    DefaultMessageSource messageSource = new DefaultMessageSource();
    // src/main/resources/messages_lang.properties
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("ru"));
    return localeResolver;
  }

  @Bean
  public MessageSourceAccessor getMessageSourceAccessor(final MessageSource messageSource) {
    return new MessageSourceAccessor(messageSource, new Locale("ru"));
  }
}
