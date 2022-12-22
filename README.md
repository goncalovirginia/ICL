# Interpretation and Compilation of Languages

### Progress

- Interpreter and Compiler for Level 1 Language implemented
- Parser for Level 2 Language implemented

### Syntax Example
````
{
    let mut i = 0;

    while (100 > ~i) {
        println ~i;
        i = ~i + 1
    };

    if (~i == 999) {
        println true
    }
    else {
        println false
    }
}
;;
````
Prints out:  
````
0  
1  
...  
98  
99  
false
````

### Compiling and Executing

#### Interpreter
- ...\ICL\src> `javac Interpreter.java`
- ...\ICL> `java -classpath src Interpreter`

#### Compiler
- ...\ICL\src> `javac Compiler.java`
- ...\ICL> `java -classpath src Compiler <source file name> <output file name>`
    - Example: `java -classpath src Compiler Source4 Program4`
- ...\ICL> `java Program4`