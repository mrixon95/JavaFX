# Basic Input and Output

### Exceptions

Many input/output methods can raise exceptions and there are 2 approaches to handling them.

- LBYL - Look before you Leap

  eg. Check an object is not null

- EAFP - Easy to Ask for Forgiveness than Permission

  Go ahead and perform the operation and then respond to an exception is raised.

Trapping and handling exceptions can be done using a try and catch block.

### Look before you Leap - LBYL

```java
if(y != 0) {
            return x/y;
        } else {
            return 0;
        }
```



### Easy to Ask for Forgiveness than Permission - EAFP

```java
try {
            return x/y;
        } catch(ArithmeticException e) {
            return 0;
        }
```

The code attempts the division and will catch the Exception if it is raised.



Here if we write text not numbers then the raised exception will be a java.util.InputMisMatchException

```java
public static void main(String[] args) {
    int x = getInt();
    System.out.println("x is " + x);
}

private static int getInt() {
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
}
```



Here is a java.lang.ArithmeticException: / by zero is raised.

```
System.out.println(1/0);
```



Another look before you leap example.

```java
private static int getIntLBYL() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter an integer: ");
        String input = sc.next();
        for(int i=0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))) {
                isValid = false;
                break;
            }
        }

        if(isValid) {
            return Integer.parseInt(input);
        }
        return 0;

    }
```



Less code in the Easier to Ask for Forgiveness than Permissions

```java
private static int getIntEAFP() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an integer: ");
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
        
    }
```



Define Exception: An event which occurs during the execution of the program which disrupts the flow of the programs instructions

When catching an exception you usually specify which subclass of exception we are catching.

For example, here we could have java.lang.ArithmeticException or java.util.InputMismatchException.

Java prints the stack trace (shows the call stack) which is all the method calls at a particular point in the program's execution.

When there is an exception, the call stack is all the methods being called when the program crashed.



```java
package com.timbuchalka;

import java.util.Scanner;

public class Example {

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x = getInt();
        int y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x/y;
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer");
        return s.nextInt();
    }
}
```





### print call stack

![printcallstack](./images/printcallstack.PNG)



When a method is called it is placed on top of the stack and when it returns it is removed from the stack. We call the divide which calls the getInt which calls the nextInt method as can be seen in the call stack when the program crashed.

subclasses of exceptions exist to explain the exception.



```java
private static int divide() {
        int x = getInt();
        int y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x/y;
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer: ");

        while(true) {
            try {
                return s.nextInt();
            } catch(InputMismatchException e) {
                // go around again. Read oass the end of line in the input first
                s.nextLine();
                System.out.println("Please enter a number using only the digits 0 through 9");
            }
        }
    }
```



CTRL+D closes the programs input stream.



### Multicatch exceptions

```java
    private static int divide() {
        int x; int y;
        try {
            x = getInt();
            y = getInt();
        } catch (NoSuchElementException e){
            throw new ArithmeticException("No suitable input");
        }

        System.out.println("x is " + x + ", y is " + y);

        try {
            return x/y;
        } catch(ArithmeticException e) {
            throw new ArithmeticException("Attempt to divide by zero");
        }


    }
    
    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer: ");

        while(true) {
            try {
                return s.nextInt();
            } catch(InputMismatchException e) {
                // go around again. Read oass the end of line in the input first
                s.nextLine();
                System.out.println("Please enter a number using only the digits 0 through 9");
            }
        }
    }
```



### Multicatch exceptions

```java
private static int divide() {
        int x; int y;
        try {
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + ", y is " + y);
            return x/y;
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("No suitable input");
        } catch(ArithmeticException e) {
            throw new ArithmeticException("Attempt to divide by zero");
        }


    }
```

Once an exception is catched all remaining exceptions are ignored.



```java
public static void main(String[] args) {

        try {
            int result = divide();
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }


    }
```





```java
public static void main(String[] args) {

        try {
            int result = divide();
            System.out.println(result);
        } catch (ArithmeticException | NoSuchElementException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    private static int divide() {
        int x;
        int y;

        x = getInt();
        y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer: ");

        while(true) {
            try {
                return s.nextInt();
            } catch(InputMismatchException e) {
                // go around again. Read oass the end of line in the input first
                s.nextLine();
                System.out.println("Please enter a number using only the digits 0 through 9");
            }
        }

    }
```



### Input involves reading data from a source and output involves writing to a destination.



When storing classes it may be more appropriate to use a binary format for string the data. Character is the correct type for XML or JSON data.



Sequential data has a set order to reading in the data. eg. a text file

Random access data is not ordered, eg. a database 



The static initialisation block is only executed once when the class is loaded.



Data can be corrupted when the file is not closed.



Can't ignore checked exceptions.

The finally block always run.

```java
public static void main(String[] args) throws IOException {
        FileWriter localFile = null;
        localFile = new FileWriter("locations.txt");
        try {
            for(Location location: locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("In catch block");
            e.printStackTrace();
        } finally {
            try {
                if(localFile != null) {
                    System.out.println("Attempting to close locfile");
                    localFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
```



![inputoutputstatic](./images/inputoutputstatic.PNG)





FileNotFoundException when a folder already exists called locations.txt and a file is trying to be created by the program called locations.txt.

FileNotFoundException is a subclass of the IOException class.

![filenotfoundexception](.\images\filenotfoundexception.PNG)



```java
public static void main(String[] args) throws IOException {
        FileWriter localFile = null;
        localFile = new FileWriter("locations.txt");
        try {
            for(Location location: locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("In catch block");
            e.printStackTrace();
        } finally {
            try {
                if(localFile != null) {
                    System.out.println("Attempting to close locfile");
                    localFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
```





Write the first line and then throw an IOException. This checks that the file is closed after the IOException is thrown and the file is saved gracefully.

```
public static void main(String[] args) throws IOException {
    FileWriter localFile = null;
    localFile = new FileWriter("locations.txt");
    try {
        for(Location location: locations.values()) {
            localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            throw new IOException("test exception thrown while writing");
        }
    } finally {
            if(localFile != null) {
                System.out.println("Attempting to close locfile");
                localFile.close();
            }
        }
}
```



locations.txt

```
0,You are sitting in front of a computer learning Java
```

Still processes the finally clause.



With the try with resources, the file writer is closed gracefully whether or not an exception is raised.

```java
try (FileWriter localFile = new FileWriter("locations.txt")) {
            for (Location location : locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                throw new IOException("test exception thrown while writing");
            }
        }
```





The file reader object is passed to the Scanner which then reads in the stream from the file reader. The scanner then works with the data from the FileReader stream.

```java
static {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);

                Map<String, Integer> tempExit = new HashMap<I

            }
        } catch (IOException e){
            e.printStackTrace();

        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
}
```

