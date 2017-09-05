// Reverse digits of an integer.

// Example1: x = 123, return 321
// Example2: x = -123, return -321int 


class Solution {
    public int reverseInteger(int input){
		int output=0;
		int sign=1;
		if (input<0) sign =-1;
		input = input*sign; 
		while (input>0){  
		   output = output*10 + input%10;
		   input = input/10;
		} 
		System.out.println("output");
		return output*sign;
	}

	public static void main() {
    	Solution s = new Solution();
		s.reverseInteger(1230);
   }
}

