package class010;

// 将两个升序链表合并为一个新的 升序 链表并返回
// 新链表是通过拼接给定的两个链表的所有节点组成的
// 测试链接 : https://leetcode.cn/problems/merge-two-sorted-lists/
public class MergeTwoLists {

  /** 链表节点定义。 不要提交此类，题目已提供。 */
  public static class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
      this.val = val;
    }

    public ListNode(int val, ListNode next) {
      this(val);
      this.next = next;
    }
  }

  /** 方案类，用于合并两个有序链表。 */
  public static class Solution {

    /**
     * 将两个升序链表合并为一个新的升序链表，并返回新链表头节点。
     *
     * <p>通过比较两个链表当前节点值，小者接入结果链表，直至一方耗尽， 最后将剩余节点全部拼接到结果链表尾。
     *
     * @param head1 第一个升序链表头节点
     * @param head2 第二个升序链表头节点
     * @return 合并后的升序链表头节点
     */
    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
      if (head1 == null) {
        return head2;
      }
      if (head2 == null) {
        return head1;
      }
      // 确定合并链表的初始头节点
      ListNode head = head1.val <= head2.val ? head1 : head2;
      // 分别指向两个链表下一比较位置
      ListNode cur1 = head == head1 ? head1.next : head1;
      ListNode cur2 = head == head2 ? head2.next : head2;
      ListNode pre = head;
      // 交替合并
      while (cur1 != null && cur2 != null) {
        if (cur1.val <= cur2.val) {
          pre.next = cur1;
          cur1 = cur1.next;
        } else {
          pre.next = cur2;
          cur2 = cur2.next;
        }
        pre = pre.next;
      }
      // 拼接剩余部分
      pre.next = cur1 != null ? cur1 : cur2;
      return head;
    }
  }
}
