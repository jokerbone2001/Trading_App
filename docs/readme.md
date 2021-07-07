# Java Style Guide
Our style mainly follows the [Code Conventions for the Java Programming Language](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) and [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
Our intention is to follow good code conventions so each of our group members can easily read each other's code since it would be normalized, it would also apply to others if they were to follow the two conventions used above.

## Table of Contents
  * [Coding Style](#coding-style)
      - [Formatting](#formatting)
      - [Indent style and braces](#indent-style-and-braces)
      - [Variable Names](#variable-names)
      - [Class Names](#class-names)
      - [Documentation](#documentation)
* [References](#references)
## Coding Style
#### Formatting

1. We try not to exceed the 100 column limit but it rarely happens such as when accessing SQLite functions which usually exceeds the limit.
2. Block comments are allowed to explain a chunk of code that would usually take up more lines than needed.

#### Indent style and braces
**Usually one indent is good enough but to randomly spam indents at random areas are a bad coding style.**
```Java
//Good
if(x==5){
    printNum(x);
}else{
    x=0;
}

//Bad
if(x==5)
            printNum(x);
```
#### Variable Names
**Meaningful names that clearly represents what it is and what it would do.**
```Java
//Good
// indicates a String variable that would contain the functions a EditText
String passwordTxt = findViewById(R.id.eTextPass); 

//Bad
// "abc" has no indications that would lead to userPassword and as code gets larger the more confusion would arise.
String abc = userPassword;
```
**Also to follow camel case.**
```java
//Good 
String goodCamelCasing;

//Bad
String Badexample;
```
#### Class Names
**Following [UpperCamelCase](https://google.github.io/styleguide/javaguide.html#s5.3-camel-case) Convention.**
```Java
//Good
public class MainActivity extends AppCompatActivity {
    ...
}
//Bad
public class itemWarehousenumber{
    ...
}
```
#### Documentation
**As mentioned in formatting, describe main functionality of functions, variables, and confusing code in general.**
```Java
//Good
/*
this class contains the Database info which has getters and setters for login buttons
*/
public class UserClass{
    ...
}
//Bad
/*
This is a user class, stores stuff
*/
public class UserClass{
    ...
}
```
## References
- Oracle. "Coding Conventions for Java TM Programming Language"
https://www.oracle.com/java/technologies/javase/codeconventions-contents.html (accessed July 6,2021).
- Google. "Google Java Style Guide"
https://google.github.io/styleguide/javaguide.html#s3-source-file-structure (accessed July 6, 2021).
