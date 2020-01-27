package javaargs.cleanercode.args;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue = false;

  public static boolean getValue(ArgumentMarshaler marshaler) {
    boolean booleanValueNew = false;
    
    if (marshaler != null && marshaler instanceof BooleanArgumentMarshaler) {
      booleanValueNew = ((BooleanArgumentMarshaler) marshaler).booleanValue;
    }
    
    return  booleanValueNew;
    }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }

}

