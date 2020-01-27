package javaargs.cleanercode.args;

import java.util.*;

import static javaargs.cleanercode.args.ArgsException.ErrorCode.*;

public class Args {
  private String schema;
  private String[] args;	
  private ListIterator<String> currentArgument;
  private List<String> argsList;
  private Map<Character, ArgumentMarshaler> givenMarshalers = 
  	new HashMap<Character, ArgumentMarshaler>();
  private Set<Character> argsFound = new HashSet<Character>();
  private Map<String, ArgumentMarshaler> allMarshalers = 
  	new HashMap<String, ArgumentMarshaler>();

  public Args(String schema, String[] args) throws ArgsException {
    this.args = args;
    this.schema = schema;
    argsList = Arrays.asList(args);
    parseStrings();
  } 

  private void parseStrings() throws ArgsException {
  	// Checking the values in args for testing.
    // for (int i=0;i<args.length;i++)
    // 	System.out.printf("ArgsList-> %d %s\n",i,args[i]);
    makeMarshalersDictionary();
    parseSchema();
    parseArgumentStrings();
  }

  private void makeMarshalersDictionary() {
  	allMarshalers.put("*", new StringArgumentMarshaler());
  	allMarshalers.put("#", new IntegerArgumentMarshaler());
  	allMarshalers.put("##", new DoubleArgumentMarshaler());
  	allMarshalers.put("[*]", new StringArrayArgumentMarshaler());
  	allMarshalers.put("&", new MapArgumentMarshaler());
  }

  private void parseSchema() throws ArgsException {
    for (String element : schema.split(",")) {
      if (element.length() > 0)
        parseSchemaElement(element.trim());
	}
  }

  private void parseSchemaElement(String element) throws ArgsException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);
    validateSchemaElementId(elementId);
    insertMarshalers(elementId, elementTail);
    // System.out.printf("ElementTail-> %s %s\n",elementTail,element);
  }


  private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId))
      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
  }

  private void insertMarshalers(char elementId, String elementTail) throws ArgsException {
  	if (elementTail.length() == 0) {
  		givenMarshalers.put(elementId, new BooleanArgumentMarshaler());
  	}
  	else if (allMarshalers.containsKey(elementTail)) {
  		givenMarshalers.put(elementId, allMarshalers.get(elementTail));
  	}
  	else {
  		throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);	
  	}
  }

  private void parseArgumentStrings() throws ArgsException {

    for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
      String argString = currentArgument.next();
      if (argString.startsWith("-")) {
        parseArgumentCharacters(argString.substring(1));
      } else {
        currentArgument.previous();
        break;
      }
    }
  }

  private void parseArgumentCharacters(String argChars) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++) {
      char argChar = argChars.charAt(i);
      ArgumentMarshaler marshaler = givenMarshalers.get(argChar);
      if (marshaler == null) {
        throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
      } 
      else {
        argsFound.add(argChar);
        try {
          marshaler.set(currentArgument);
        } 
        catch (ArgsException exception) {
          exception.setErrorArgumentId(argChar);
          throw exception;
        }
      }
    }
  }

  public boolean has(char argument) {
    return argsFound.contains(argument);
  }

  public int nextArgument() {
    return currentArgument.nextIndex();
  }

  public boolean getBoolean(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return BooleanArgumentMarshaler.getValue(marshaler);
  }

  public String getString(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return StringArgumentMarshaler.getValue(marshaler);
  }

  public int getInt(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return IntegerArgumentMarshaler.getValue(marshaler);
  }

  public double getDouble(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return DoubleArgumentMarshaler.getValue(marshaler);
  }

  public String[] getStringArray(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return StringArrayArgumentMarshaler.getValue(marshaler);
  }

  public Map<String, String> getMap(char argument) {
  	ArgumentMarshaler marshaler = givenMarshalers.get(argument);
    return MapArgumentMarshaler.getValue(marshaler);
  }
}