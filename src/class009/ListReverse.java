package class009;

// 按值传递、按引用传递
// 从堆栈角度解释链表节点
// 以堆栈视角来看链表反转
/** 演示 Java 参数传递机制与链表反转操作： - 值类型（基本类型、String）按值传递 - 引用类型按引用传递，但引用本身按值传递 - 单、双链表反转示例 */
public class ListReverse {

  /**
   * 程序入口：验证基本类型、对象引用和数组的传递机制，并演示链表反转。
   *
   * @param args 未使用的命令行参数
   */
  public static void main(String[] args) {
    // int、long、byte、short、char、float、double、boolean 及 String 按值传递
    int a = 10;
    f(a);
    System.out.println(a);

    // 自定义对象按引用传递，但引用本身按值传递
    Number b = new Number(5);
    g1(b);
    System.out.println(b.val);
    g2(b);
    System.out.println(b.val);

    // 数组按引用传递，但引用本身按值传递
    int[] c = {1, 2, 3, 4};
    g3(c);
    System.out.println(c[0]);
    g4(c);
    System.out.println(c[0]);

    // 示例：反转双链表
    DoubleListNode dblHead = new DoubleListNode(1);
    dblHead.next = new DoubleListNode(2);
    dblHead.next.last = dblHead;
    DoubleListNode reversedDbl = reverseDoubleList(dblHead);
    System.out.println("双链表反转后头节点值：" + reversedDbl.value);

    // 示例：反转单链表
    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
    ListNode reversed = Solution.reverseList(head);
    System.out.println("单链表反转后头节点值：" + reversed.val);
  }

  /**
   * 演示基本类型按值传递，不影响原变量。
   *
   * @param a 基本类型
   */
  public static void f(int a) {
    a = 0;
  }

  /**
   * 演示对象引用被重新赋值，但不影响调用者的引用变量。
   *
   * @param b 对象引用
   */
  public static void g1(Number b) {
    b = null;
  }

  /**
   * 演示通过引用修改对象内部状态，调用者可见。
   *
   * @param b 对象引用
   */
  public static void g2(Number b) {
    b.val = 6;
  }

  /**
   * 演示数组引用被重新赋值，但不影响调用者的引用变量。
   *
   * @param c 数组引用
   */
  public static void g3(int[] c) {
    c = null;
  }

  /**
   * 演示通过数组引用修改元素值，调用者可见。
   *
   * @param c 数组引用
   */
  public static void g4(int[] c) {
    c[0] = 100;
  }

  /**
   * 反转双链表。
   *
   * @param head 原双链表头节点
   * @return 反转后链表的新头节点
   */
  public static DoubleListNode reverseDoubleList(DoubleListNode head) {
    DoubleListNode pre = null;
    DoubleListNode next;
    while (head != null) {
      next = head.next;
      head.next = pre;
      head.last = next;
      pre = head;
      head = next;
    }
    return pre;
  }

  /** 自定义数字类型，用于演示引用传递。 */
  public static class Number {
    public int val;

    public Number(int v) {
      val = v;
    }
  }

  /** 单链表节点定义。 */
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

  /** 双链表节点定义。 */
  public static class DoubleListNode {
    public int value;
    public DoubleListNode last;
    public DoubleListNode next;

    public DoubleListNode(int v) {
      value = v;
    }
  }

  /** 单链表反转实现。 */
  public static class Solution {

    /**
     * 反转单链表。
     *
     * @param head 原链表头节点
     * @return 反转后链表的新头节点
     */
    public static ListNode reverseList(ListNode head) {
      ListNode pre = null;
      ListNode next;
      while (head != null) {
        next = head.next;
        head.next = pre;
        pre = head;
        head = next;
      }
      return pre;
    }
  }
}
