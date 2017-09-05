# Java Notes

- when given an input, always check if it's valid or not first.
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

