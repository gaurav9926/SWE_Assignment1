package javaargs.cleanercode.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";
  private Integer intValue = 0;
  private Double doubleValue = 0.0;

  public static String getValue(ArgumentMarshaler marshaler) {
    String stringValue = "";
    if (marshaler != null && marshaler instanceof StringArgumentMarshaler) {
      stringValue = ((StringArgumentMarshaler) marshaler).stringValue;
    }
    return stringValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } 
    catch (NoSuchElementException exception) {
      throw new ArgsException(MISSING_STRING);
    }
  }
}

