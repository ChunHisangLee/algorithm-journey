package class034;

// 返回兩個無環單鏈表相交的第一個節點
// 要求：時間複雜度 O(n)，額外空間複雜度 O(1)
// 測試連結 : https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class Code01_IntersectionOfTwoLinkedLists {

  /**
   * 雙指針法： 1. 分別讓 pA、pB 指向兩條鏈表頭 2. 同時向後移動，當到達尾部時切換到另一條鏈表的頭 3. 最多走過兩條鏈表長度之和，若相交則會在交點相遇，否則最終皆為 null
   *
   * @param headA 第一條鏈表頭
   * @param headB 第二條鏈表頭
   * @return 相交的第一個節點，若無交點返回 null
   */
  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode pA = headA;
    ListNode pB = headB;

    // 當兩指標不相等時，持續前進或切換頭部
    while (pA != pB) {
      // 若到達末尾，切換到另一條鏈表的頭
      pA = (pA == null) ? headB : pA.next;
      pB = (pB == null) ? headA : pB.next;
    }
    // 若相交，pA (或 pB) 為交點；否則最終皆為 null
    return pA;
  }

  // 單鏈表節點定義
  public static class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }
}
