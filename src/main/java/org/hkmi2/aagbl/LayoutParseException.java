package org.hkmi2.aagbl;

public class LayoutParseException extends Exception
{

  public LayoutParseException() {
  }

  public LayoutParseException(String message) {
    super(message);
  }

  public LayoutParseException(Throwable cause) {
    super(cause);
  }

  public LayoutParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public LayoutParseException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
