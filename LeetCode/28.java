/*
28. Implement strStr()

Implement strStr().

这道题就是让你判断，needle是不是haystack的子串，是的话就返回这个子串。

解题想法是，从haystack的第一个位置，开始逐个判断是不是子串。如果整个子串都匹配了，那么就返回，否则继续往下挪位置。

注意要看haystack剩余的长度跟needle比足不足够多，不够的话也就不用往后比了。

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

*/
class Solution {
    public int strStr(String haystack, String needle) {
    	// or to use The KMP matching algorithm
    	if(needle.length() > haystack.length()) return -1;
        if(needle.length() == 0) return 0;

        for(int i = 0; i < haystack.length(); i++) {
        	if(haystack.length() - i < needle.length()) return -1;

        	int k = i;
        	int j = 0;
        	while(j < needle.length() && k < haystack.length()) {
        		if(needle.charAt(j) == haystack.charAt(k)) {
        			j++;
        			k++;
        		} else {
                    break;
                }
        		if(j == needle.length()) {
        			return i;
        		}
        	}
        }
        return -1;
    }
}

// Time: O(n1 * n2) 
// Space: O(1)


// Better solution
public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}