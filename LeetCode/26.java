/*
26. Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element appear only once and
return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.

*/
class Solution {
    public static int removeDuplicates(int[] nums) {
        // corner cases first
        if(nums.length == 0) return 0;
        else if(nums.length == 1) return 1;
        
        int prev = nums[0];
        int j = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != prev) {
                nums[j] = nums[i];
                prev = nums[i];
                j++;
            }
        }
        return j;
    }
}