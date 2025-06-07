package class011;

// 给你两个 非空 的链表，表示两个非负的整数
// 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
// 测试链接：https://leetcode.cn/problems/add-two-numbers/
public class AddTwoNumbers {

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
         * Adds two numbers represented by linked lists in reverse order.
         *
         * @param l1 head of the first list
         * @param l2 head of the second list
         * @return head of the list representing their sum
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            int carry = 0;

            // Traverse both lists until both are exhausted and no carry remains
            while (l1 != null || l2 != null || carry != 0) {
                int sum = carry;
                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }
                // Compute a new digit and carry
                carry = sum / 10;
                int digit = sum % 10;

                // Append new node
                tail.next = new ListNode(digit);
                tail = tail.next;
            }

            return dummy.next;
        }
    }
}
