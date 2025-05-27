package class012;


// 给你一个链表的头节点 head 和一个特定值 x
// 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
// 你应当 保留 两个分区中每个节点的初始相对位置
// 测试链接 : https://leetcode.cn/problems/partition-list/
public class PartitionList {

  /** 链表节点定义。题目已提供，无需提交此类。 */
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

  /** 方案类，用于将链表按值分区。 */
  public static class Solution {

    /**
     * 分隔链表，使得所有小于 x 的节点都出现在大于等于 x 的节点之前，并保留相对顺序。
     *
     * <p>使用两个链表分别存储小于 x 与大于等于 x 的节点，遍历原链表时断开并追加到相应链表， 最后将两部分拼接即可。
     *
     * @param head 原链表头节点
     * @param x 分区值
     * @return 分隔后链表的新头节点
     */
    public static ListNode partition(ListNode head, int x) {
      ListNode leftHead = null, leftTail = null; // < x 区域
      ListNode rightHead = null, rightTail = null; // >= x 区域
      while (head != null) {
        ListNode next = head.next;
        head.next = null; // 断开当前节点
        if (head.val < x) {
          if (leftHead == null) {
            leftHead = head;
          } else {
            leftTail.next = head;
          }
          leftTail = head;
        } else {
          if (rightHead == null) {
            rightHead = head;
          } else {
            rightTail.next = head;
          }
          rightTail = head;
        }
        head = next;
      }
      // 若小于 x 区域为空，直接返回大区头
      if (leftHead == null) {
        return rightHead;
      }
      // 拼接两部分
      leftTail.next = rightHead;
      return leftHead;
    }
  }
}
