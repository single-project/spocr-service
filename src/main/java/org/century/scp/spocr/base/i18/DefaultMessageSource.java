package org.century.scp.spocr.base.i18;

import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageSource extends ReloadableResourceBundleMessageSource {

  public DefaultMessageSource() {
    setUseCodeAsDefaultMessage(true);
  }

  public String getMessage(String key) {
    return getMessageWithCurrentLocale(key, null);
  }

  public String getMessage(String key, Object[] args) {
    return getMessageWithCurrentLocale(key, args);
  }

  private String getMessageWithCurrentLocale(String key, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return getMessage(key, args, locale);
  }


}
