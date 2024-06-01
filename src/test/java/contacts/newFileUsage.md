### Using System.getProperty("user.dir") for File Path Determination

Both the JsonPath constructor and the .body() method can take a File object as a parameter. 
This File object represents the file path you specified and is used to read the contents of that file. 
Let's explain how these processes work more concretely:

1. JsonPath Constructor
   
       File jsonExample = new File(System.getProperty("user.dir"), "src/test/resources/jsonexample2.json");
       JsonPath jsonPath = new JsonPath(jsonExample);
   What Happens Here:
   File Object Creation: new File(System.getProperty("user.dir"), "src/test/resources/jsonexample2.json") creates 
   a File object representing the path to jsonexample2.json in your project.
   JsonPath Initialization: new JsonPath(jsonExample) takes this File object as a parameter.
   The JsonPath constructor reads the content of the file located at the path represented by jsonExample.
   The JSON content is then parsed and loaded into a JsonPath object, allowing you to query the JSON data using JsonPath expressions.
2. .body() Method
   
       String addContact = "src/test/resources/contact.json";
       given().body(new File(addContact));
   What Happens Here:
   File Object Creation: new File(addContact) creates a File object representing the path to contact.json in your project.
   Setting Request Body: .body(new File(addContact)) takes this File object as a parameter.
   The .body() method reads the content of the file located at the path represented by addContact.
   The content of the contact.json file is then used as the body of the HTTP request being constructed.
   
   Summary
   File Object: In both cases, new File(path) creates a File object that represents the file at the specified path.
   For JsonPath: The File object is used to locate and read the JSON file. 
   The content of the file is parsed into a JsonPath object for querying.
   For .body(): The File object is used to locate and read the file. 
   The content of the file is used as the body of an HTTP request.
   Concrete Explanation:
   File Object Creation:

       File jsonExample = new File(System.getProperty("user.dir"), "src/test/resources/jsonexample2.json");
This File object represents the file located at /path/to/project/src/test/resources/jsonexample2.json.

    File contactFile = new File("src/test/resources/contact.json");
This File object represents the file located at /path/to/project/src/test/resources/contact.json.
    
   Using File with JsonPath:

      JsonPath jsonPath = new JsonPath(jsonExample);
The constructor reads the JSON content from the file represented by jsonExample.
The content is loaded into a JsonPath object for querying.

   Using File with .body():

  .body(new File(addContact));
The .body() method reads the content from the file represented by addContact.
The content is used as the body of the HTTP request.
In both scenarios, the File object provides a way to reference and read the content of a file at a specific path, 
enabling further operations such as JSON parsing or setting the request body in an HTTP request.

///// //// //// 
   
    Advantages of Using System.getProperty("user.dir"):
   Dynamic Path: Using System.getProperty("user.dir") dynamically determines the root directory of your project. 
   This ensures that the file path is correctly determined when you run your project on different systems or directories.
   Portability: It allows your code to find the correct file path regardless of where the project root directory is 
   located when you move your code to another computer or run it in CI/CD environments.
   
    Advantages of Using Direct File Paths:
   Simplicity: If the directory where your tests will run is fixed and unchanging, 
   specifying the file path directly can be simpler and more understandable.
   Speed: There's no need to dynamically determine the project root directory every time, 
   which can make file access slightly faster.
   
Test 1:
   
       @Test
       public void testFlatJson() {
       // Define the file path to the JSON resource
       // Load the source file
       File jsonExample = new File(System.getProperty("user.dir"), "src/test/resources/jsonexample2.json");
    
       // Convert the JSON content into a JsonPath object
       JsonPath jsonPath = new JsonPath(jsonExample);
       }
   Test 2:
   
       @Test
       @DisplayName("Create Contact")
           public void createContact() {
           String addContact = "src/test/resources/contact.json"; // Contact data file
           String url = app + "/api/v1/contacts"; // Contacts API endpoint
           String token = getJWTToken();
           given().
           body(new File(addContact)). // Set request body
           ...
           }

    1-File jsonExample = new File(System.getProperty("user.dir"), "src/test/resources/jsonexample2.json");
    JsonPath jsonPath = new JsonPath(jsonExample);

    2-String addContact = "src/test/resources/contact.json";
    given().
    body(new File(addContact)).



Project Structure and Working Directory
Project Structure:
plaintext
Copy code
my-project/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── resources/
│           └── contact.json
├── build/
└── pom.xml
Fixed File Path Usage and Working Directory
If you specify a fixed file path in your test code, this path will be resolved relative to 
your current working directory (the directory from which you run the command).

Test Code with Fixed File Path:

    @Test
    public void createContact() {
    // Fixed file path
    String addContact = "src/test/resources/contact.json";
    File contactFile = new File(addContact);
    
        // Check if the file exists
        if (contactFile.exists()) {
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
    }
Working Directory Examples:
1. Working Directory: Project Root Directory
   If you are in the my-project directory and run the command from there:


    cd my-project
    java -cp src/test/resources:src/main/java org.junit.runner.JUnitCore createContactTest
In this case, addContact = "src/test/resources/contact.json" will work correctly and the file will be found because:

    my-project/src/test/resources/contact.json
is a valid path.

2. Working Directory: Outside the Project Root (e.g., build Directory)
   If you are in the build directory and run the command from there:


    cd my-project/build
    java -cp ../src/test/resources:../src/main/java org.junit.runner.JUnitCore createContactTest
In this case, addContact = "src/test/resources/contact.json" will not work because the path will be resolved as:


    my-project/build/src/test/resources/contact.json
which does not exist, resulting in "File not found."

Dynamic File Path Usage:
To avoid such errors, it is safer to use a dynamic file path. This can be done using System.getProperty("user.dir").

Test Code with Dynamic File Path:

    @Test
    public void createContact() {
    // Dynamic file path
    File contactFile = new File(System.getProperty("user.dir"), "src/test/resources/contact.json");
    
        // Check if the file exists
        if (contactFile.exists()) {
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
    }
Dynamic File Path and Working Directory Examples:
1. Working Directory: Project Root Directory
   
       cd my-project
       java -cp src/test/resources:src/main/java org.junit.runner.JUnitCore createContactTest
   In this case, System.getProperty("user.dir") returns the project root directory (my-project), and the path:


    my-project/src/test/resources/contact.json
is constructed and the file is found.

2. Working Directory: Outside the Project Root (e.g., build Directory)
   
       cd my-project/build
       java -cp ../src/test/resources:../src/main/java org.junit.runner.JUnitCore createContactTest
   Even in this case, System.getProperty("user.dir") still returns the project root directory (my-project), and the path:

    my-project/src/test/resources/contact.json
is constructed and the file is found.

Summary:
Current Working Directory: 
The directory from which you run the command. File paths are resolved relative to this directory.
Fixed File Path: If you specify a fixed file path, it may not work correctly if your working directory is 
not the project root.
Dynamic File Path: Using System.getProperty("user.dir") to dynamically determine the project root directory 
ensures that file paths work correctly regardless of the working directory.
Thus, using a dynamic file path is important to ensure that your file paths work correctly 
when your project is run from different directories.
