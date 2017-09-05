/*
38. Count and Say

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"

Example 2:
Input: 4
Output: "1211"
*/
class Solution {
    public String countAndSay(int n) {
    	if(n < 0) return "-1";	

		String res = "1";
		// recursion: countAndSay(n) = countAndSay(countAndSay(n-1))
		for(int i = 1; i < n; i++) {
			res = countSay(res);
		}
    	return res;
        
    }

    public String countSay(String s) {
    	// StringBuilder.append() is the default way to append one string to another
    	StringBuilder res = new StringBuilder();
		char cur = s.charAt(0);
		int cnt = 1;
        
        if(s.length() == 1) {
            res.append(1).append(s.charAt(0));
            return res.toString();
        }
        
    	for(int i = 1; i < s.length(); i++) {
    		if(s.charAt(i) == cur) {
    			cnt++;
    		} else {
    			res.append(cnt).append(cur); // StringBuilder can append int or string
    			cnt = 1;
    			cur = s.charAt(i);
    		}
            if(i == s.length() - 1) {
                res.append(cnt).append(cur);
            }
    	}
    	return res.toString();
    }
}