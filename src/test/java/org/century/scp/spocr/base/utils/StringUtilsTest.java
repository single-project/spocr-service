package org.century.scp.spocr.base.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

  @Test
  public void getIndexIfCharLocatedAtBegining() {
    char[] ops = {'>', '<'};
    String q = ">123";
    Assert.assertEquals(0, StringUtils.getIndexIfContainsAny(q, ops));
  }

  @Test
  public void getIndexIfCharLocatedAtEnd() {
    char[] ops = {'>', '<'};
    String q = "123>";
    Assert.assertEquals(3, StringUtils.getIndexIfContainsAny(q, ops));
  }

  @Test
  public void getIndexIfCharLocatedInMiddle() {
    char[] ops = {'>', '<'};
    String q = "12>3";
    Assert.assertEquals(2, StringUtils.getIndexIfContainsAny(q, ops));
  }

  @Test
  public void getNoIndexIfStringNotContainChar() {
    char[] ops = {'>', '<'};
    String q = "123";
    Assert.assertEquals(-1, StringUtils.getIndexIfContainsAny(q, ops));
  }
}
