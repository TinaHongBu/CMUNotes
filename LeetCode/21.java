/*
21. Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.
*/
/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // corner cases first
        // null lists in this case
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        // Use recursion
        // Every time you find the smaller one as the merge head meanwhile it's the last recursive procession's next node. 
        ListNode newHead;
        if(l1.val <= l2.val) {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next, l2);
        } else {
            newHead = l2;
            newHead.next = mergeTwoLists(l2.next, l1);
        }
        return newHead;
    }
    
}

