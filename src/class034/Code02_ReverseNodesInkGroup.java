package class034;

// 每 k 個節點一組翻轉鏈表
// 要求：時間複雜度 O(n)，額外空間複雜度 O(1)
// 測試連結 : https://leetcode.cn/problems/reverse-nodes-in-k-group/
public class Code02_ReverseNodesInkGroup {

  /**
   * 使用虛擬頭節點(dummy)簡化頭部處理，迭代反轉每個 k 節點子鏈表 步驟： 1. 計算鏈表長度 2. 預先創建 dummy 指向 head，並用 prevGroupEnd
   * 標記前一組尾節點 3. 對於每組 k 節點： - 確定 start = prevGroupEnd.next, end = move(start, k-1) - 保存
   * nextGroupHead = end.next - 反轉區間 [start, end] - 令 prevGroupEnd.next = end，start.next =
   * nextGroupHead (連接後續) - 更新 prevGroupEnd = start 4. 返回 dummy.next
   *
   * @param head 原始鏈表頭
   * @param k 每組大小
   * @return 處理後的新鏈表頭
   */
  public static ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k <= 1) return head;

    // dummy 節點
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prevGroupEnd = dummy;

    // 計算鏈表總長
    int length = 0;
    ListNode p = head;
    while (p != null) {
      length++;
      p = p.next;
    }

    // 處理每個一組長度為 k 的子鏈表
    int groups = length / k;
    ListNode start, end, nextGroupHead;
    p = dummy;
    for (int i = 0; i < groups; i++) {
      start = prevGroupEnd.next;
      end = move(start, k - 1);
      nextGroupHead = end.next;
      // 斷開並反轉[start, end]
      end.next = null;
      ListNode revHead = reverseList(start);
      // 連接上前後
      prevGroupEnd.next = revHead;
      start.next = nextGroupHead;
      // 更新 prevGroupEnd
      prevGroupEnd = start;
    }

    return dummy.next;
  }

  /** 向後移動步數步，返回到達節點；若不足，返回最後節點 */
  private static ListNode move(ListNode node, int steps) {
    while (steps-- > 0 && node != null) {
      node = node.next;
    }
    return node;
  }

  /** 反轉整條單鏈表，返回新的頭節點 */
  private static ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;
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
