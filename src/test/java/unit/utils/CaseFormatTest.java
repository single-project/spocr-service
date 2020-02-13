package unit.utils;

import static org.junit.Assert.assertEquals;

import com.google.common.base.CaseFormat;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.junit.Test;

public class CaseFormatTest {

  @Test
  public void formatLowerCamelToLowerKebab() {
    String string = LegalType.class.getSimpleName();
    String result = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(string);
    assertEquals("legal-type", result);
  }
}
