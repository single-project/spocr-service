package org.century.scp.spocr.exceptions;

public class SpocrEntityNotFoundException extends SpocrException {

  public SpocrEntityNotFoundException(long id, String entityName) {
    super("Элемент '" + entityName + "' с кодом [" + id + "] не найден");
  }
}
