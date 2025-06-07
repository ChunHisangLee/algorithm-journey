package class010;

// 将两个升序链表合并为一个新的 升序 链表并返回
// 新链表是通过拼接给定的两个链表的所有节点组成的
// 测试链接 : https://leetcode.cn/problems/merge-two-sorted-lists/
public class MergeTwoLists {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	class Solution {
        /**
         * Merges two sorted singly-linked lists and returns the head of the merged list.
         *
         * @param head1 the head of the first sorted list
         * @param head2 the head of the second sorted list
         * @return the head of the merged sorted list
         */
        public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
            // Dummy node to simplify edge cases
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            // While both lists have nodes, append the smaller one
            while (head1 != null && head2 != null) {
                if (head1.val <= head2.val) {
                    tail.next = head1;
                    head1 = head1.next;
                } else {
                    tail.next = head2;
                    head2 = head2.next;
                }
                tail = tail.next;
            }

            // Attach the remaining nodes (only one of these will be non-null)
            if (head1 != null) {
                tail.next = head1;
            } else {
                tail.next = head2;
            }

            return dummy.next;
        }
    }
}
