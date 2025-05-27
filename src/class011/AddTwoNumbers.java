package class011;

// 给你两个 非空 的链表，表示两个非负的整数
// 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
// 测试链接：https://leetcode.cn/problems/add-two-numbers/
public class AddTwoNumbers {

  /** 链表节点定义。题目已提供，此处仅为示例。 */
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

  /** 方案类，用于实现两个逆序存储的数相加。 */
  public static class Solution {

    /**
     * 将两个以逆序方式存储的非空链表表示的非负整数相加，返回相同形式的新链表。
     *
     * <p>逐位相加并处理进位，若最后有余进位则追加新节点。 不复用原节点，所有节点均为新创建，便于教学说明。
     *
     * @param h1 第一个链表头节点
     * @param h2 第二个链表头节点
     * @return 表示和的逆序链表头节点
     */
    public static ListNode addTwoNumbers(ListNode h1, ListNode h2) {
      ListNode ans = null, cur = null;
      int carry = 0;
      // 同时遍历两个链表
      for (int sum, val;
          h1 != null || h2 != null;
          h1 = (h1 != null ? h1.next : null), h2 = (h2 != null ? h2.next : null)) {
        sum = (h1 != null ? h1.val : 0) + (h2 != null ? h2.val : 0) + carry;
        val = sum % 10;
        carry = sum / 10;
        if (ans == null) {
          ans = new ListNode(val);
          cur = ans;
        } else {
          cur.next = new ListNode(val);
          cur = cur.next;
        }
      }
      // 最后若有进位则追加
      if (carry > 0) {
        cur.next = new ListNode(carry);
      }
      return ans;
    }
  }
}
