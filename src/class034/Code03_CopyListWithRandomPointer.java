package class034;

// 複製帶隨機指標的鏈表
// 要求：時間複雜度 O(n)，額外空間 O(1)
// 測試連結 : https://leetcode.cn/problems/copy-list-with-random-pointer/
public class Code03_CopyListWithRandomPointer {

  /**
   * 三步走方法： 1. 交織複製：對原鏈表每個節點 N，將複製節點 N' 插入 N 之後 2. 設置 random：新節點 N'.random = N.random.next (若
   * N.random 不為 null) 3. 拆分鏈表：將原鏈表與複製鏈表還原並拆分
   *
   * @param head 原鏈表頭
   * @return 複製後的新鏈表頭
   */
  public static Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    // 1. 交織複製節點
    Node cur = head;
    while (cur != null) {
      Node next = cur.next;
      Node copy = new Node(cur.val);
      cur.next = copy;
      copy.next = next;
      cur = next;
    }
    // 2. 設置 random
    cur = head;
    while (cur != null) {
      Node copy = cur.next;
      copy.random = (cur.random != null) ? cur.random.next : null;
      cur = copy.next;
    }
    // 3. 拆分兩條鏈表
    cur = head;
    Node newHead = head.next;
    while (cur != null) {
      Node copy = cur.next;
      cur.next = copy.next;
      copy.next = (copy.next != null) ? copy.next.next : null;
      cur = cur.next;
    }
    return newHead;
  }

  // 定義帶 random 指標的節點
  public static class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
      this.val = val;
    }
  }
}
