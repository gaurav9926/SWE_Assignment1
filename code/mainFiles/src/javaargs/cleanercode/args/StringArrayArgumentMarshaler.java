package javaargs.cleanercode.args;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

import java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
  private List<String> strings = new ArrayList<String>();

  public static String[] getValue(ArgumentMarshaler marshaler) {
    String[] strValue = new String[0];
    if (marshaler != null && marshaler instanceof StringArrayArgumentMarshaler) {
      strValue = ((StringArrayArgumentMarshaler) marshaler).strings.toArray(new String[0]);
    }
    return strValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      strings.add(currentArgument.next());
    } 
    catch (NoSuchElementException exception) {
      throw new ArgsException(MISSING_STRING);
    }
  }
}
