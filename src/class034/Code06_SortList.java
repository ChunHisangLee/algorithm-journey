package class034;

// 排序链表
// 要求时间复杂度O(n*logn)，额外空间复杂度O(1)，还要求稳定性
// 数组排序做不到，链表排序可以
// 测试链接 : https://leetcode.cn/problems/sort-list/
public class Code06_SortList {

    // 链表节点定义
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用自底向上的归并排序（迭代版），时间复杂度O(n log n)，空间复杂度O(1)，且保持稳定性
     * 思路：
     * 1. 首先遍历链表计算长度 n
     * 2. 使用一个哨兵节点(dummy)简化合并操作的头部连接
     * 3. 以步长 step 从1开始，每次翻倍：
     * - 将链表按 step 划分成若干对相邻子链表(left, right)
     * - 每次提取 left = curr 子链表，right = split(left, step)；
     * nextSub = split(right, step)，并断开连接
     * - 合并 left 与 right，将合并后的链表连接到 prev.next，并更新 prev 至合并尾部
     * - curr = nextSub，继续下一对合并
     * 4. 最终 dummy.next 即为排好序的链表头
     * <p>
     * 关键函数：
     * split(head, size) : 从 head 开始向后走 size 步并断开，返回下一段链表的头
     * merge(a, b, prev) : 合并两段有序链表 a 和 b，将结果接到 prev.next，返回合并后链表的尾节点
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 1. 计算链表长度 n
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }

        // 2. 哨兵节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 3. 自底向上合并，步长从1开始，每次翻倍
        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;
            while (curr != null) {
                // 左半部分：长度为 step 的子链表
                ListNode left = curr;
                // 右半部分：从左半部分后面开始的长度为 step 的子链表
                ListNode right = split(left, step);
                // 下一个子链表的起点
                curr = split(right, step);
                // 合并 left 和 right 两段，并接到 prev
                prev = merge(left, right, prev);
            }
        }
        return dummy.next;
    }

    // 从 head 按长度 size 切断一段，返回后续子链表的头；若不够 size，则返回 null
    private static ListNode split(ListNode head, int size) {
        if (head == null) return null;
        for (int i = 1; head.next != null && i < size; i++) {
            head = head.next;
        }
        ListNode next = head.next;
        head.next = null; // 切断
        return next;
    }

    // 合并有序链表 a, b，将结果接到 prev.next，返回合并后尾节点
    private static ListNode merge(ListNode a, ListNode b, ListNode prev) {
        ListNode curr = prev;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        // 剩余部分直接接上
        curr.next = (a != null) ? a : b;
        // 移动到合并后的尾部
        while (curr.next != null) curr = curr.next;
        return curr;
    }
}
