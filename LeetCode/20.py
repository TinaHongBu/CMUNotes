
# 20. Valid Parentheses

# Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

# The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.



def isValid(s):
    """
    :type s: str
    :rtype: bool
    """
    stack = []
    pair = {"(":")", "{":"}", "[":"]"}
    for ch in s:
        if ch in pair:
            stack.append(pair[ch])
        else:
            if not stack or stack.pop() != ch:
                return False
    return not stack

s = "(){[]}"

print(isValid(s))
