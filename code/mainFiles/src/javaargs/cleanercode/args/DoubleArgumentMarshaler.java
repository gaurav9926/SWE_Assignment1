package javaargs.cleanercode.args;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

import java.util.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double doubleValue = 0;

  public static double getValue(ArgumentMarshaler marshaler) {
    double doubleArgValue = 0.0;
    if (marshaler != null && marshaler instanceof DoubleArgumentMarshaler) {
      doubleArgValue = ((DoubleArgumentMarshaler) marshaler).doubleValue;
    }
    return doubleArgValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } 
    catch (NoSuchElementException exception) {
      throw new ArgsException(MISSING_DOUBLE);
    } 
    catch (NumberFormatException exception) {
      throw new ArgsException(INVALID_DOUBLE, parameter);
    }
  }

}
