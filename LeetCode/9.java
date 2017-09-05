// Determine whether an integer is a palindrome. Do this without extra space.
/*
Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(12));
        System.out.println(isPalindrome(32123)); 
        System.out.println(isPalindrome(321123)); 
        System.out.println(isPalindrome(-1)); 
        System.out.println(isPalindrome(1234567));
        System.out.println(isPalindrome(1000000021));
    }

    /*
    Solution#1 compare the int with the reversed int
    */
    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        if(reverseInt(x) == x)
            return true;
        else return false;
    }
    
    public static int reverseInt(int x) {
        int output = 0;
        if(x < 0)
            return 0;
        while(x > 0) {
            output = output * 10 + x % 10;
            x = x / 10;
        }
        return output;
    }

    /*
    Solution#2 Compare first and last digit
    */

    public static boolean isPalindrom(int num) {
        if (num < 0) return false;
        /*find the most significant digit*/
        int div = 1;
        while (num / div >= 10) div *= 10; // two digits
        // 先找到有几位
        
        while (num != 0) { // stop till num is 0, all digits compared
            int l = num / div; // left digit 最高位
            int r = num % 10; // right digit 最末位
            if (l != r) return false; // compare
            num = (num % div) / 10; // remove first and last digit
            div /= 100; // div should be smaller
        }
        return true;
    }
 
}