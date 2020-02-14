package org.century.scp.spocr.base.i18;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMessageSource extends ReloadableResourceBundleMessageSource {

  public String getMessage(String key) {
    Locale locale = LocaleContextHolder.getLocale();
    return getMessage(key, null, locale);
  }
}
