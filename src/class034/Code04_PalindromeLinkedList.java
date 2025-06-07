package class034;

// 判斷單鏈表是否為回文結構
// 要求：時間複雜度 O(n)，額外空間 O(1)，最後恢復鏈表原狀
// 測試連結 : https://leetcode.cn/problems/palindrome-linked-list/
public class Code04_PalindromeLinkedList {

  /**
   * 主方法： 1. 快慢指針尋找鏈表中點 2. 反轉中點之後的後半部分鏈表 3. 比對前後兩段節點值 4. 反轉回原鏈表並返回結果
   *
   * @param head 鏈表頭
   * @return 是否為回文
   */
  public static boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    // 1. 找到中點（前半尾）
    ListNode mid = findMiddle(head);
    // 2. 反轉後半部分
    ListNode secondHead = reverseList(mid.next);
    // 暫時斷開前後
    mid.next = null;

    // 3. 比對前半與反轉後的後半
    ListNode p1 = head;
    ListNode p2 = secondHead;
    boolean palindrome = true;
    while (p1 != null && p2 != null) {
      if (p1.val != p2.val) {
        palindrome = false;
        break;
      }
      p1 = p1.next;
      p2 = p2.next;
    }

    // 4. 恢復後半鏈表並連回原鏈表
    ListNode restored = reverseList(secondHead);
    mid.next = restored;

    return palindrome;
  }

  /** 使用快慢指針找到鏈表前半部分的尾節點 當鏈表長度為偶數時，slow 指向左中點 */
  private static ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /** 原地反轉單鏈表，返回新的頭節點 */
  private static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
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
