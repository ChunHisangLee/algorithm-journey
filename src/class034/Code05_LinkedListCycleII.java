package class034;

// 偵測單鏈表中的環並返回入環的第一個節點
// 要求：時間複雜度 O(n)，空間複雜度 O(1)
// 測試連結 : https://leetcode.cn/problems/linked-list-cycle-ii/
public class Code05_LinkedListCycleII {

  /**
   * 使用快慢雙指針找環： 1. slow 每次走一步，fast 每次走兩步，若相遇則有環 2. 在相遇點，將 fast 指回 head，然後 slow 與 fast 同速前進 3.
   * 第二次相遇即為入環的第一個節點
   *
   * @param head 鏈表頭
   * @return 入環的第一個節點，若無環則返回 null
   */
  public static ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head;

    // 第一次相遇判斷是否存在環
    do {
      if (fast == null || fast.next == null) {
        return null;
      }
      slow = slow.next;
      fast = fast.next.next;
    } while (slow != fast);

    // 將 fast 重置為 head，與 slow 同速前進
    fast = head;
    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }
    // fast == slow 為入環第一個節點
    return fast;
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
