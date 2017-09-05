/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

// How Roman Numeral Works
//The things is to add items that is equal to or less than its previous one, and minus items that is less than its next one.


import javax.swing.*;
class Solution {

    public static void main(String args[]) {
      System.out.print(romanToInt("XIII"));
   }

    public static int romanToInt(String s) {
        int res = findIntFromRoman(s.charAt(0));
        int prev = res;
        for(int i = 1; i < s.length(); i++) {
            int cur = findIntFromRoman(s.charAt(i));
            if(cur <= prev)
                res += cur;
            else {
                res += cur;
                res -= prev * 2;
            }
            prev = cur;
        }
        return res;
    }
    
    public static int findIntFromRoman(char c) {
        int res = 0;
        switch(c) {
            // Use single quotes for literal chars, double quotes for literal Strings
            case 'I': res = 1; break;
            case 'V': res = 5; break;
            case 'X': res = 10; break;
            case 'L': res = 50; break;
            case 'C': res = 100; break;
            case 'D': res = 500; break;
            case 'M': res = 1000; break;
        }
        return res;
    }
}