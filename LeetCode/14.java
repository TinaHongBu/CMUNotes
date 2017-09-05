/*
Write a function to find the longest common prefix string amongst an array of strings.

For example:

{"a","a","b"} should give "" as there is nothing common in all the 3 strings.

{"a", "a"} should give "a" as a is longest common prefix in all the strings.

{"abca", "abc"} as abc

{"ac", "ac", "a", "a"} as a.

Logic goes something like this:

Pick a character at i=0th location and compare it with the character at that location in every string.

If anyone doesn't have that just return ""

Else append that character in to the result.

Increment i and do steps 1-3 till the length of that string.

return result.

Make sure proper checks are maintained to avoid index out of bounds error.
*/
public String longestCommonPrefix(String[] strs) {
    String res = "";
    boolean flag = true;
    if(strs.length == 0) return "";
    for(int i = 0; i < strs[0].length(); i++) {
        for(int j = 1; j < strs.length; j++){
            if(strs[j].length() <= i) {
                flag = false;
                System.out.println("shorter");
                break;
            }
            if(strs[j].charAt(i) != strs[j-1].charAt(i)) {
                flag = false;
                break;
            }
        }
        if(flag) res += strs[0].charAt(i);
    }
    return res;
}


// Better Solution that doesn't use a flag
public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0) return "";
    String pre = strs[0];
    int i = 1;
    while(i < strs.length) {
        while(strs[i].indexOf(pre) != 0) 
            pre = pre.substring(0, pre.length() - 1);
        i++;
    }
    return pre;
}

// sort the array first, then only compare the first and last one
// convert the first and last string to char arrays

public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0) return "";
    Arrays.sort(strs);
    char[] first = strs[0].toCharArray();
    char[] last = strs[strs.length - 1].toCharArray();
    
    String res = "";
    for(int i = 0; i < first.length(); i++) {
        if(first[i] == last[i])
            res += first[i]; 
    }
    return res;

}

public String longestCommonPrefix(String[] strs) {
    if (strs == null) return null;
    if (strs.length == 0) return "";
    
    Arrays.sort(strs);
    char[] first = strs[0].toCharArray();
    char[] last  = strs[strs.length - 1].toCharArray();
     
    int i = 0, len = Math.min(first.length, last.length);
    while (i < len && first[i] == last[i]) i++;
    return strs[0].substring(0, i);
}
