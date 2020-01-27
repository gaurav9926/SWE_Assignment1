package javaargs.cleanercode.args;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

public class MapArgumentMarshaler implements ArgumentMarshaler {
  private Map<String, String> map = new HashMap<>();


  public static Map<String, String> getValue(ArgumentMarshaler marshaler) {
    Map<String, String> mapValues = new HashMap<>(); 
    if (marshaler != null && marshaler instanceof MapArgumentMarshaler) {
      mapValues = ((MapArgumentMarshaler) marshaler).map;
    }
    return mapValues;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      String[] mapEntries = currentArgument.next().split(",");
      for (String entry : mapEntries) {
        String[] entryComponents = entry.split(":");
        if (entryComponents.length != 2) {
          throw new ArgsException(MALFORMED_MAP);
        }
        map.put(entryComponents[0], entryComponents[1]);
      }
    } 
    catch (NoSuchElementException exception) {
      throw new ArgsException(MISSING_MAP);
    }
  }
}
