package org.century.scp.spocr.events.models;

import org.century.scp.spocr.exceptions.SpocrException;

public enum SearchOperation {
  GREATER_THAN;

  public static final String[] OPERATION_SET = {">"};

  public static SearchOperation getOperation(char input) {
    switch (input) {
      case '>':
        return GREATER_THAN;
      default:
        throw new SpocrException(
            "Неизвестная операция [" + input + "] в запросе поиска",
            new UnsupportedOperationException());
    }
  }
}
