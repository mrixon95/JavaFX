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

The scanner's close method takes care of any stream it was using, provide that the stream implements the closeable interface. Automatically handled when the scanner closes.



A buffered reader reads text in from an input stream and buffers the characters into a character array. We will use the Buffered Reader as an input stream for the file to use.



```java
try (FileWriter localFile = new FileWriter("locations.txt");
        FileWriter dirFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");

                for (String direction: location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }

            }
        }
```



The file Reader is read into the Buffered Reader and the Buffered Reader is read into the Scanner.

Closing the Scanner will close the Buffered Reader and Closing the Buffered Reader will close the File Reader.

The Buffered Reader is both Readable and Closeable so it reads and closes on 

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

                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));

            }

        } catch (IOException e){
            e.printStackTrace();

        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }


        // Now read the exits

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            scanner.useDelimiter(",");

            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String direction = scanner.next();
                scanner.skip(scanner.delimiter());
                String dest = scanner.nextLine();
                int destination = Integer.parseInt(dest);
                System.out.println(loc + ": " + direction + ": " + destination);

                Location location = locations.get(loc);
                location.addExit(direction, destination);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
```





## Code for the locations and Main



### Main class

```java
public class Main {

    private static Locations locations = new Locations();


    public static void main(String[] args) {
        // Change the program to allow players to type full words, or phrases, then move to the
        // correct location based upon their input.
        // The player should be able to type commands such as "Go West", "run South", or just "East"
        // and the program will move to the appropriate location if there is one.  As at present, an
        // attempt to move in an invalid direction should print a message and remain in the same place.
        //
        // Single letter commands (N, W, S, E, Q) should still be available.

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = 64;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }

    }
}
```



### Locations

```java
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

        try (FileWriter localFile = new FileWriter("locations_big.txt");
        FileWriter dirFile = new FileWriter("directions_big.txt")) {
            for (Location location : locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");

                for (String direction: location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }

            }
        }
    }


    static {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations_big.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);

                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));

            }

        } catch (IOException e){
            e.printStackTrace();

        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }


        // Now read the exits

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
            scanner.useDelimiter(",");

            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());
//                String dest = scanner.nextLine();
//                int destination = Integer.parseInt(dest);

                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);


                System.out.println(loc + ": " + direction + ": " + destination);

                Location location = locations.get(loc);
                location.addExit(direction, destination);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }


//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));
//
//
    }


    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}

```





### Location

```java
public class Location {

    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new HashMap<String, Integer>(exits);
        } else {
            this.exits = new HashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }

//    public void addExit(String direction, int location) {
//        exits.put(direction, location);
//    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<String, Integer>(exits);
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

}
```





Buffered reader is optimised to read in chunks at a time.

```
// data is only read from the memory when the buffer is empty
// data is only written to file when the buffer writer is full
```



```java
try (BufferedWriter localFile = new BufferedWriter(new FileWriter("locations_big.txt"));
             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions_big.txt"))) {
            for (Location location : locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");

                for (String direction: location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }

            }
        }
```



Buffered reader reads large chunks of data into memory in the case of a read operation.



The code in the static block is executed before the main method.

The advantage of using binary data is that we do not need to parse it into the variables to be stored. Byte stream can be used to read and write any of the primitive types.



Writing bytestreams.

```java
public static void main(String[] args) throws IOException {


        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location: locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
                locFile.writeInt(location.getExits().size() - 1);

                for(String direction: location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }

            }
        }
```



Buffered reader ensures that large chunks of data are read into the buffered reader and once it becomes full, it gets emptied.

When writing data, only once the Buffered Writer is full will its contents be written to file. Data is written in sizeable chunks.



## Reading Binary Data and EOF Exceptions

EOF exception is thrown when the end of the file is reached.



### Main.java

```java
public class Main {

    private static Locations locations = new Locations();


    public static void main(String[] args) {
        // Change the program to allow players to type full words, or phrases, then move to the
        // correct location based upon their input.
        // The player should be able to type commands such as "Go West", "run South", or just "East"
        // and the program will move to the appropriate location if there is one.  As at present, an
        // attempt to move in an invalid direction should print a message and remain in the same place.
        //
        // Single letter commands (N, W, S, E, Q) should still be available.

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = 64;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }

    }
}
```





### Locations.java

```java
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {


        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location: locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
                locFile.writeInt(location.getExits().size() - 1);

                for(String direction: location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }

            }
        }



//        try (BufferedWriter localFile = new BufferedWriter(new FileWriter("locations_big.txt"));
//             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions_big.txt"))) {
//            for (Location location : locations.values()) {
//                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//
//                for (String direction: location.getExits().keySet()) {
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
//                    }
//                }
//
//            }
//        }
    }


    static {

        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            while(true) {
                Map<String, Integer> exits = new LinkedHashMap<String, Integer>();
                int locID = locFile.readInt();
                String description = locFile.readUTF();
                int numExits = locFile.readInt();
                System.out.println("Read location " + locID + " : " + description);
                System.out.println("Found " + numExits + " exits");

                for (int i = 0; i < numExits; i++) {
                    String direction = locFile.readUTF();
                    int destination = locFile.readInt();
                    exits.put(direction, destination);
                    System.out.println("\t\t" + direction + "," + destination);

                }

                locations.put(locID, new Location(locID, description, exits));

            }
        } catch (IOException io) {
            System.out.println("IO Exception");
        }
    }



//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
//
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//
//        }
//
//
//        // Now read the exits
//
//        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))){
//            // data is only read from the memory when the buffer is empty
//            // data is only written to file when the buffer writer is full
//            String input;
//
//            while( (input = dirFile.readLine()) != null ) {
////                int loc = scanner.nextInt();
////                scanner.skip(scanner.delimiter());
////                String direction = scanner.next();
////                scanner.skip(scanner.delimiter());
////                String dest = scanner.nextLine();
////                int destination = Integer.parseInt(dest);
//
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//
//
//                System.out.println(loc + ": " + direction + ": " + destination);
//
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));
//
//


    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
```



### Location.java

```java
public class Location {

    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new LinkedHashMap<String, Integer>(exits);
        } else {
            this.exits = new LinkedHashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }

//    public void addExit(String direction, int location) {
//        exits.put(direction, location);
//    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<String, Integer>(exits);
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

}

```



Objects need to be serialisable to be written to a file as is.

Read and write objects as a single unit using Object Input stream and Object output stream.

Objects needs to be translated to a format that can both be stored and later recreated. This is called seralisation.

We need to make the java object serialisable in order to read and write to file. Implements the serialisable interface.

Now the interface doesn't actually have any methods. It just gives the JVM a heads up that we may want to read and write the file to storage.

We also need to set a serial version uid field. Think of it as a version for the class. If we don't set it then the compiler will create it based on class details. When we read an object from a stream, the java run time checks the stored serial version uid against the one contained within the complied class file. If they don't match, then there's a compatibility problem and the run time will throw an invalid class exception.

Make location class serialisable by using the serialisable interface. It does not implement any methods. The linked hash map itself implements the serialisable interface so it can be written to file along with the primitive data types.



Read in objects and Write out objects.



```java
public static void main(String[] args) throws IOException {

        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for(Location location: locations.values()) {
                locFile.writeObject(location);
            }

        }
```





```java
    static {

        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat"))) ) {
            boolean eof = false;

            while(!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);

                } catch(EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException io) {
            System.out.println("ClassNotFoundException " + io.getMessage());
        }
    }
```



If two foo instances point to the same bar instance then only one is serialised.

The serialised file will only every contain one copy of the same instance. Object instances are unique within a file but not across files.



This time, let's read the location in everytime a player moves to that location.

This means that we need to move to the particular location in the file rather than just reading in each location sequentially.

This is where random access class coms in.

In such a situation, it would be best to use a database rather than a flat file. In this scenario we will be using a flat file.

The file pointer is an offset in the file where the next read or write will begin from.

Whenever we read/write the file, the file pointer is advanced by the number of bytes we read/write in.

Offset is the byte location in the file. If the offset (file pointer) has value 100 then the pointer is located at value 100.

First byte in the file is in the position 0. 



When using random access files we refer to each set of related data as a record.

In our application, the location id, description and exits make up the record for a location.

When a user moves to a location, how do we know which bytes to be read from the file.

The index stores the record length and offset for each location. 

We will get the index record for the location and use the index values to read in the data.



Will use the file pointer to point to where the first read/write will take place.

The file pointer is always advanced by the number of bytes read or written.

The file pointer will already be position correctly if it is after the file we read or wrote.

Only need to call the seek method when we want to jump to a different offset in the file.



Let's use a random access file and read in locations on demand.





```
write to a random access file
rwd - indicates that we are opening the file for reading and writing and that we want writes to occur snychronously
Each index record will contain 3 integers - the location ID, the offset for the location and the size/ length of the location record
```



Can't do read and write objects with a random access file.



```java
String description = ra.readUTF(); // the write UTF method writes the length of the string followed by the string itself.
        // hence readUTF can read the length of the string and the string itself.
```





```java
ra.seek(record.getStartByte()); // move the file pointer to the locations offset
// using the seek method from the random access file
```



Only reading in the locations one at a time from the Random Access File



The code



### Locations

```java
package com.timbuchalka;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile ra;

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile rao = new RandomAccessFile("locations_rand.dat", "rwd")) {
            rao.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
            rao.writeInt(locationStart);

            long indexStart = rao.getFilePointer();

            int startPointer = locationStart;
            rao.seek(startPointer);

            for(Location location : locations.values()) {
                rao.writeInt(location.getLocationID());
                rao.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                    }
                }
                rao.writeUTF(builder.toString());

                IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
                index.put(location.getLocationID(), record);

                startPointer = (int) rao.getFilePointer();
            }

            rao.seek(indexStart);
            for(Integer locationID : index.keySet()) {
                rao.writeInt(locationID);
                rao.writeInt(index.get(locationID).getStartByte());
                rao.writeInt(index.get(locationID).getLength());
            }

        }

    }

    // 1. This first four bytes will contain the number of locations (bytes 0-3)
    // 2. The next four bytes will contain the start offset of the locations section (bytes 4-7)
    // 3. The next section of the file will contain the index (the index is 1692 bytes long.  It will start at byte 8 and end at byte 1699
    // 4. The final section of the file will contain the location records (the data). It will start at byte 1700


    static {
        try {
            ra = new RandomAccessFile("locations_rand.dat", "rwd");
            int numLocations = ra.readInt();
            long locationStartPoint = ra.readInt();

            while(ra.getFilePointer() < locationStartPoint) {
                int locationId = ra.readInt();
                int locationStart = ra.readInt();
                int locationLength = ra.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
            }

        } catch(IOException e) {
            System.out.println("IOException in static initializer: " + e.getMessage());
        }
    }

    public Location getLocation(int locationId) throws IOException {

        IndexRecord record = index.get(locationId);
        ra.seek(record.getStartByte());
        int id = ra.readInt();
        String description = ra.readUTF();
        String exits = ra.readUTF();
        String[] exitPart = exits.split(",");

        Location location = new Location(locationId, description, null);

        if(locationId != 0) {
            for(int i=0; i<exitPart.length; i++) {
                System.out.println("exitPart = " + exitPart[i]);
                System.out.println("exitPart[+1] = " + exitPart[i+1]);
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExit(direction, destination);
            }
        }

        return location;
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        ra.close();
    }
}

```



### Location

```java
public class Location implements Serializable {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    private long serialVersionUID = 1L;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new LinkedHashMap<String, Integer>(exits);
        } else {
            this.exits = new LinkedHashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }

//    public void addExit(String direction, int location) {
//        exits.put(direction, location);
//    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<String, Integer>(exits);
    }
    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }
}
```





### Main

```java
public class Main {

    private static Locations locations = new Locations();


    public static void main(String[] args) throws IOException {
        // Change the program to allow players to type full words, or phrases, then move to the
        // correct location based upon their input.
        // The player should be able to type commands such as "Go West", "run South", or just "East"
        // and the program will move to the appropriate location if there is one.  As at present, an
        // attempt to move in an invalid direction should print a message and remain in the same place.
        //
        // Single letter commands (N, W, S, E, Q) should still be available.

        // File format:
        // 1. The first 4 bytes will contain the number of locations (bytes 0-3)
        // 2. The next four bytes will contain the start offset of the locations section (bytes 4-7)
        // . The next section of the file will contain the index (the index is 1692 bytes long. It will start at byte 8 and end at byte 1699.
        // 3. The next section of the file will contain the location records (the data). It will start at byte 1700.



        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = 64;
        Location currentLocation = locations.getLocation(64);
        while(true) {
            System.out.println(currentLocation.getDescription());

            // if we reach location 0 then we break out of the program
            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));

            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();

    }
}
```











### Concurrency/Threads



A process is a unit of execution that has its own memory space. Each application has its own memory space of heap. 

Process and application can be used interchangeable. Process <-> Application.

The 1st application can't access the heap that belongs to the 2nd Java application. The heap isn't shared between them. They have their own.



A thread is a unit of execution within a process. Each process can have multiple threads. In Java, every process/application has at least one thread, the main thread. For UI applications, this is called the JavaFX application thread. In fact, just about every Java process also has multiple system threads that handle tasks like memory management and I/O. We the developers don't explicitly creayte and code those threads. Our code runs on the main thread, or in other threads that we explicitly create.



Create a thread doesn't require as many resources as creating a process. Every thread created by a process shares the process' memory and files. This can create problems, as we will see in a later lecture.

In addition to the process' memory or heap, each thread has what's called a thread stack which is the memory that only that thread can access. We'll look at what's stored in the heap vs what's stored in the thread stack in a later lecture.



So, every Java applications runs as a single process, and each process can have multiple threads. Every process has a heap and every thread has a thread stack.



Why have multiple threads?

Sometimes we have a task that will take a long time eg. query a database or fetch data from the internet. If we do this on the main thread, we won't be able to do anything else whilst we wait for the process runs.

The execution of the main thread will be suspended as it queries the database or fetches data from the internet. The main thread execution will be suspended and we will just have to wait for the data to be returned before we can execute the next line of code. To the user, this suspension whilst waiting for data can make it seem like the application has died, especially with UI applications.



So instead of using the main thread and having to wait for it to complete before continuing the program, we can create another thread and execute the long-running task on that thread. By having another thread, we can free up the main thread so that it can continue executing. The main thread can also report progress or accept user input whilst the long-running task continues to execute in the back ground.

The 2nd reason to use threads is that an API may require us to provide one. Sometimes we have to provide the code that will run when a method we've called reaches a certain point in its execution. In this instance, we don't usually create a thread. We pass in the code that we want too run on the thread.

Concurrency means doing more than one thing at a time, Concurrency means that progress can be made on more than one task. 

Say we had to download data and draw a shape in an application.

If its a concurrent application, then it can download a bit of data, then switch to drawing part of the shape, then go back and download some more data, then go back and draw more of the shape and so on.

Concurrency means that one task doesn't have to complete before another can start. We can do a bit of one, and a bit of the other. Java provides thread-related classes so that we can create Java concurrent applications.

When threads are sheduled to run, we are at the mercy of the JVM and the operating system when it comes to when threads are scheduled to run.



We are going to create a subclass of the thread class and override the run method.



Create a new thread and run the start method, which will run the run method.

```java
System.out.println("Hello from the main thread");

Thread anotherThread = new AnotherThread();
anotherThread.start();

System.out.println("Hello again from the main thread");
```



```java
public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from another thread");

    }
}
```



Its up to the system to schedule the threads.

We can't reuse the same instance of a thread.

If we can start multiple times then we will get an exception

Can't guarantee order of thread execution.

![ThreadOrderOfExecution](./images/ThreadOrderOfExecution.PNG)



Another way to create a thread is by using the runnable interface. Only needs to implement the run method.

We tell the thread what to run by overriding the run method.

Anonymous class is good if you only want to run the code once. Can't start the same instance of a thread once.



```java
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable's implementation of run()");
    }
}
```



```java
Thread myRunnableThread = new Thread(new MyRunnable());
myRunnableThread.start();
```



Runs the code in the 

```java
Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                super.run();
            }
        });
```



It is preferrable to use the runnable interface since there are many methods in the Java API that want runnable instance passed to it. Also since the introduction of lambda expressions, its convenient to use anonymous runnable instances

A thread terminates when it returns from its run method.

Do not call the thread instance's run method, instead call the start method.

We implement the run method but call the start method.

The JVM will call the run method for us after we call the start method.

If we do make the mistake of calling the run method, the code will be run on the same thread that call the run method. This is instad of running the code on a new thread.



```java
public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from" + currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another thread woke me up");
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");

    }
}
```



To interrupt a thread we need to call the interrupt method on the thread instance it wants to interrupt



```java
anotherThread.interrupt();
```





```java
public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from" + currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another thread woke me up");
            return;
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");

    }
}

```



### Join

There are situations where we can't run a thread until another one has terminated. Eg. a thread needs data and won't execute until the thread that retrieves all the data has finished executing. In this case we can join the thread to the thread that is retrieving the data.

When we join thread A to thread B, thread A will wait for thread B to terminate before it will execute itself.



```java
Thread myRunnableThread = new Thread(new MyRunnable() {
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
        try {
            anotherThread.join();
            System.out.println(ANSI_RED + "AnotherThread terminated so I'm running again");
        } catch(InterruptedException e) {
            System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
        }
    }
});
```

myRunnable thread will wait for anotherThread to execute.



![CountDown](.\images\CountDown.PNG)



Thread  2 skips from 9 to 7 because thread 1 already printed 8.

Technology: The heap is the application's memory that all threads share.

Every thread has a thread stack and this is memory that only that thread can access. Thread 1 cannot access Thread 2's stack and vice cersa.

When multiple threads are working with the same object, they share the same object. When one thread changes one of the object's isntance variables, the other threads will see that value from that point onwards.

We pass the same countdown instance to both threads.

The for condition assigns the initial value to the variable and after that point just checks that the condition is met.

In the for loop, the thread can be suspended just before decrementing i, just before printing the condition or just before printing out the value.

The two threads interfere with each other, whenever one thread tries to run, the other thread has changed the value of i and so the thread starts at a different position.



```java
public class Main {

    public static void main(String[] args) {
	// use a count down object to count down to 0.
        CountDown countdown = new CountDown();

        CountDownThread t1 = new CountDownThread(countdown);
        t1.setName("Thread 1");

        CountDownThread t2 = new CountDownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }

}



class CountDown {
    private int i;

    public void doCountdown() {
        String color;

        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;

            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;

            default:
                color = ThreadColor.ANSI_GREEN;
        }

        for(i=10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
        }

    }

}

class CountDownThread extends Thread {
    private CountDown threadCountDown;

    public CountDownThread(CountDown countdown) {
        threadCountDown = countdown;
    }

    public void run() {
        threadCountDown.doCountdown();
    }

}

```



No interference when we have two countdown instances.

```java
CountDown countdown1 = new CountDown();
CountDown countdown2 = new CountDown();
```





![NoInterferenceBetweenThreads](./images/NoInterferenceBetweenThreads.PNG)



We need to allow multiple threads to access the same record, but prevent race condition (thread interference).

The process of controlling when threads execute code and therefore access the heap (process memory) is called synronisation.

When a method is synchronised, only one thread can execute that at a time. When a thread is executing the method, all other methods that want to call that method will suspend until the method running the thread exits it. Then another synchronised method can run the method, and another. Only one thread can run a synchronised method at a time.

To create threads: we just have to create an object subclass from thread and call the start method.

Or we can create an instance of a thread with a runnable object.





## To prevent a race condition

Race condition is where 2 or more threads are updating the same resource. To synchronise a method, we add the synchronized keyword to the method declaration. We want the whole method to run before another method gets access to it.

Without synchronization, multiple threads can access the same method at the same time.

The process of controlling when a thread executes code and therefore when they can access the processes' memory (the heap) is called synchronization.



![WithoutSynchronization](./images/WithoutSynchronization.PNG)





With synchronization, only one thread at a time can run

the same method. We need to synchronize all areas where interference can happen.



![WithSynchronization](./images/WithSynchronization.PNG)



We can also synchronize a block of statements.

Every Java object has an intrinsic lock. We can synchronise a block of statements which work with an object by forcing each thread to have to acquire the lock to the object before the execute the statement block. Only one thread can hold the lock at a time so the other threads will be suspended until the other thread releases it. Primitive types don't have a lock.

Local variables are stored in the thread stack. Each thread will create its own copy of the local variable and each copy is an object with its own lock. Thread 1 gets its own copy and the lock for its own copy and continues executing. Thread 2 gets its own copy and the lock for its own copy and continues executing. When synchronizing, we need to use an object that both the threads share. Don't use local vartiables for synchronization. Object values are stored on the heap but the reference is stored in the thread stack.

```java
synchronized(color) {
    for (i = 10; i > 0; i--) {
        System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
    }
}
```

vs

```java
synchronized(this) {
    for (i = 10; i > 0; i--) {
        System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
    }
}
```



We can synchronise static methods and static objects, the lock is owned by the class object associated with the objects class. 

Critical section is that code which references a shared resource like a variable. Only one thread should be able to access it at a time.

A class is thread safe if the developer has syncronised all the ciritical sections within the code so we as a developer do not need to worry about thread interference.

anotherThread.join(3000) will wait for anotherThread to finish executing or for 3 sconds to elapse. Whatever happens first.

A for loop consists of several steps and a thread can be suspended between any of the steps and another thread starts running. The threads keep on interfering with eachother because whenever they get a chance to run the other thread has changed the value of i.

