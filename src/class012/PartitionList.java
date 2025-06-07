package class012;

// 给你一个链表的头节点 head 和一个特定值 x
// 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
// 你应当 保留 两个分区中每个节点的初始相对位置
// 测试链接 : https://leetcode.cn/problems/partition-list/
public class PartitionList {

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
         * Partitions a list so that all nodes less than x come before nodes greater than or equal to x,
         * preserving the original relative order.
         *
         * @param head the head of the input list
         * @param x    the pivot value
         * @return the head of the partitioned list
         */
        public ListNode partition(ListNode head, int x) {
            // Dummy heads for before and after lists
            ListNode beforeDummy = new ListNode(0);
            ListNode afterDummy = new ListNode(0);
            ListNode before = beforeDummy;
            ListNode after = afterDummy;

            // Partition nodes into before and after lists
            while (head != null) {
                if (head.val < x) {
                    before.next = head;
                    before = before.next;
                } else {
                    after.next = head;
                    after = after.next;
                }
                head = head.next;
            }

            // Terminate the 'after' list
            after.next = null;
            // Link before a list to an after list
            before.next = afterDummy.next;

            return beforeDummy.next;
        }
    }
}
