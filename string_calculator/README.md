# string_calculator
A calculator which takes a string input with a mathematical expression of any length and outputs the result.

### How it works

The calculator works as follows:
1. Take a string input from user
2. Tokenize the input, splitting it into:
    * numbers: any sequence of digits with an optional minus sign in front and optional decimal part after a dot
    * operators: any character from the list of supported operators (currently +, -, *, /)
    * brackets: ( and )
    * functions: any sequence of letters, upper- or lowercase
    * spaces are ignored  
    Note on using ```-```: when followed by a space (e.g. ```- 5```), it is parsed as the subtraction operator; when followed immediately by a number (e.g. ```-5```), the number is parsed as a negative number  
3. Convert the tokenized input from infix to postfix notation using the [shunting yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
4. Calculate the postfix expression using a [stack-based evaluation algorithm](https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_evaluation_algorithm)
5. Output the result.

### Install

```bash
[string_calculator]$ mvn package
```

### Run

```bash
[string_calculator]$ java -jar main/target/main-0.3.0-jar-with-dependencies.jar
```
or
```bash
[string_calculator]$ ./run.sh
```