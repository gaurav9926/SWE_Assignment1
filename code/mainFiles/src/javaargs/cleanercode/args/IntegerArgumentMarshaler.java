package javaargs.cleanercode.args;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

import java.util.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
  private int intValue = 0;
  private double doubleValue = 0.0;
  private String parameter = null;


  public void checkingDouble() throws ArgsException {
    try {  
      doubleValue = Double.parseDouble(parameter);
      // System.out.println("Value = " + doubleValue);
      throw new ArgsException(DOUBLE_INTEGER, parameter);
    }
    catch (NumberFormatException exception_new) {
      throw new ArgsException(INVALID_INTEGER, parameter);
    }

  }
  public static int getValue(ArgumentMarshaler marshaler) {
    int integerValue = 0;
    if (marshaler != null && marshaler instanceof IntegerArgumentMarshaler) {
      integerValue = ((IntegerArgumentMarshaler) marshaler).intValue;
    }
    return integerValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    parameter = null;
    try {
      parameter = currentArgument.next();
      intValue = Integer.parseInt(parameter);
    } 
    catch (NoSuchElementException exception) {
      throw new ArgsException(MISSING_INTEGER);
    } 
    catch (NumberFormatException exception) {
      // System.out.printf("ArgsList-> %d\n",intValue);
      checkingDouble();
    }
  }

}
