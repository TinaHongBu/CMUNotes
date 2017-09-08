/*
53. Maximum Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*/
class Solution {
    public int maxSubArray(int[] nums) {
        
        // corner cases
        if(nums.length == 0) return 0;
        
        int max = nums[0];
        if(nums.length == 1) return nums[0];
                
        for(int i = 0; i < nums.length; i++){
            int tmp = 0;
            for(int j = 0; j < nums.length - i; j++) {
                tmp += nums[i + j];
                if(tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }
}