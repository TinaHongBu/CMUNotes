/*
20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

public static boolean isValid(String s) {
    // if odd length, definitely not matching
    if(s.length() % 2 == 1) return false;
    
    Stack<Character> stack = new Stack<Character>();
    // if meet a left parentheses, push the corresponding right on in the stack
    for(char c : s.toCharArray()) {
        if(c == '(') stack.push(')');
        else if(c == '[') stack.push(']');
        else if(c == '{') stack.push('}');
        // if meet a right parentheses, check if it's the same as the pop value of the stack
        // invalid situation can also be having an empty stack
        else if(stack.isEmpty() || stack.pop() != c)
            return false;
    }
    return stack.isEmpty();
}