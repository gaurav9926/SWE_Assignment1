# Software Engineering             
### Assignment-1 : JavaArgs Code Cleanup
### Gaurav Aswani (20171107)

#### Introduction:

This is my version of the javaargs package by unclebob (https://github.com/unclebob/javaargs).
This package implements different features which  displays different values of the given set of **parameters**.
This package displays the command line arguments passed to the file when run using the java command. For example,
- When the command line argument is **-l**, then **logging** is returned **true**, else **false**.
- For the command line argument **-p [val]**, then the value of **port** is returned to the val given in the **bracket**, else it is **zero**.
- For the command line argument **-d [name]**, then the name of the **directory** is returned to the name given in the **bracket**, else it is **null**.

The other  command line arguments can be the following, 
(which can be used by changing **the schema** in the **ArgsMain file**):
* Schema:
    - char    - Boolean arg.
    - char*   - String arg.
    - char#   - Integer arg.
    - char##  - double arg.
    - char[*] - one element of a string array.


* Example schema: (f,s*,n#,a##,p[*])
Coresponding command line: **"-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3**

#### How To Run:
-First we need to  install **ant** by the following command:
* install ant by running **'sudo apt-get install ant'**

-Then to compile the code go to the folder where you have cloned this repo and run the following commands:
  * run **'ant compile'**
  * run **'ant jar'**
 
-To run the code, we have to run the **ArgsMain.java** file using the given below java command:
  * run **'java -cp build/jar/args.jar javaargs.cleanercode.args.ArgsMain [command line arguments]'**
  * The output would depend on the arguments.
    * For example:
        > Input: java -cp build/jar/args.jar javaargs.cleanercode.args.ArgsMain -l'
          \> Output: logging is true, port:0, direcory:
         Inut: java -cp build/jar/args.jar javaargs.cleanercode.args.ArgsMain -p "8080"'
         \> Output: logging is false, port: 8080, directory:
         And other combinations of these three values as we have chosen our schema as:
        "l, p#, d*" (if this is our schema)

-Now to run the tests, we have to run the following command from the root of the folder:
* **java -cp "lib/junit-4.13.jar:lib/hamcrest-**
**core-1.3.jar:build/jar/args.jar" ../mainFiles/test/javaargs/cleanercode/args/ArgsTest.java [name of test function]**

#### My Implementations:
- Made the **schema** and the **args** variable as the **class variables** in the Args.java file such that they don't need to be passed to every method of the class explicitly. So now the functions parseSchema and parseArgumentStrings have **no variable** to be passed which earlier had 1 argument passed to them have no arguments passed to them now.
- Made a new **parseStrings** function which would call the functions parseSchema and parseArgumentStrings. This would improve the readability of the code and would also improve abstraction in the code. 
- Divided the function **parseSchemaElement** to **two functions** parseSchemaElement and insertMarshalers, now parseSchemaElement is validating the schema element and insertMarshalers is inserting the schema elemnt into the marshaler which was earlier doing two functions.
- Also made argsList as an new variable which would be used when calling the method parseArgumentStrings();
- Improved the variable and the function names to signify more meaning to the code and to follow the verb-noun naming pattern. 
-  If one function calls another function inside it then the called function will be below the callee function to enable easy readibility of code. (A calls B, then B lies below A).

#### Out Of The Box:
- Changed the **directory structure** to separately represent the documentation and the code structure.

- Change the **name of the package**, which was designed from a website so it had a different name, to something which resonated with the actual implementation and design.

- Made a new hashmap, allMArshalers, which maps all the schema elements which could be present to the corresponding Marshaler functions such as **"\*"->"StringArgumentMarshaler"** , etc. This increases the readability of the method parseSchemaElement and also decreases the cyclomatic complexity of the code.

- This also increases the **readability of the parseSchemaElement** function as now it is broken down to two functions and also which earlier had **5 if-else** statements and is now reduced to only **1 if-else** statement.


#### Test Cases

- **testDoubleWithInteger()**: Made a new unit test to check if error is returned when a double value is passed to an integer schema element. It returns an exception.
 
- **testIntegerWithDouble()**: Checking if Double schema argument works when given an Integer value. It works correctly.
 
- **testDoubleWithNegativeInteger()**: Checking if double works when given a negative integer value. Works correctly.
 
-  **testNoArgumentWithString()**: Checking if giving no arguments to a string schema element returns a error or not. Returns a error.

  
 
