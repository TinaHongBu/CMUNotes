# Use a reduce function

# The function reduce(func, seq) continually applies the function func() to the sequence seq
# Takes the result of the first 2 components as the first parameter in the next func()
# If seq = [ s1, s2, s3, ... , sn ], and func = (a + b)
# Then it goes from s1 + s2, to s3 + (s1 + s2) ...


def lcp(str1, str2):
    i = 0
    while (i < len(str1) and i < len(str2)):
        if str1[i] == str2[i]:
            i = i+1
        else:
            break
    return str1[:i]

# @return a string                                                                                                                                                          
def longestCommonPrefix(strs):
    if not strs:
        return ''
    else:
        return reduce(lcp, strs)


# or use
# def longestCommonPrefix(strs):
#     def lcp(s, t):
#         if len(s)>len(t):
#             s, t = t, s
#         for i in range(len(s)):
#             if s[i]!=t[i]:
#                 return s[:i]
#         return s
#     return reduce(lcp, strs) if strs else ""


print longestCommonPrefix(["abcd", "ab", "a"])