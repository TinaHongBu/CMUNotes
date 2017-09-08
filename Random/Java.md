# Java Notes

- when given an input, always check if it's valid or not first
  - Array: if(array == null || array.length == 0) return "invalid";

|                Difference                | **Java**                                 | **Python**                       |
| :--------------------------------------: | :--------------------------------------- | :------------------------------- |
|              String Length               | string.length()                          | len(string)                      |
|               Array Length               | array.length                             | len(arr)                         |
|               List Length                | arraylist.size()                         | len(list), len(tuple)            |
| String -> char array (for string comparison) | char[] c = string.toCharArray();         | for s in string (or list(sting)) |
|            String compairson             | a.equals(b); a == b checks object references | a == b                           |
|               function def               | public static boolean isValid(String s) {} | def isValid(s):                  |
|                 Boolean                  | true, false                              | True, False                      |
|                  Stack                   | Stack<Character> stack = new Stack<Character>(); stack.push(), stack.pop() | list.append(), list.pop()        |
|                                          |                                          |                                  |

- String Value comparison: s1.equals(s2)

  x.equals(y) means the references x and y are holding objects that are equal

  x==y means that the references x and y have same object.

- Always check if the character set is ASCII

- Check if is space

```java
if (c == ' ') // char is a primitive data type, so it can be compared with ==
if (s == " ") // " " is a String consant now, can be compared with ==
```

- It's always easier to modify the string from the behind to the beginning

