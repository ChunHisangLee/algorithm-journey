package class013;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 演示队列和栈的多种实现方式： - 使用 Java 内置 LinkedList/Stack - 基于数组的固定容量实现 - 循环队列设计 */
public class QueueStackAndCircularQueue {

  /**
   * 基于 Java 内置 LinkedList 的队列实现。
   *
   * <p>内部使用双向链表，操作均为 O(1) 但常数较大。
   */
  public static class Queue1 {
    /** 底层双向链表实现 */
    public Queue<Integer> queue = new LinkedList<>();

    /**
     * 判断队列是否为空。
     *
     * @return 若为空返回 true，否则 false
     */
    public boolean isEmpty() {
      return queue.isEmpty();
    }

    /**
     * 向队列尾部添加元素。
     *
     * @param num 要添加的整数
     */
    public void offer(int num) {
      queue.offer(num);
    }

    /**
     * 从队列头部移除并返回元素。
     *
     * @return 头部元素或 null
     */
    public int poll() {
      return queue.poll();
    }

    /**
     * 查看队列头部元素但不移除。
     *
     * @return 头部元素或 null
     */
    public int peek() {
      return queue.peek();
    }

    /**
     * 返回当前队列长度。
     *
     * @return 队列中元素个数
     */
    public int size() {
      return queue.size();
    }
  }

  /**
   * 基于固定大小数组的队列实现。
   *
   * <p>如果能明确上限 n，所有操作均为 O(1)。
   */
  public static class Queue2 {
    private final int[] queue;
    private int l;
    private int r;

    /**
     * 构造方法，指定最大容量。
     *
     * @param n 最大加入次数
     */
    public Queue2(int n) {
      queue = new int[n];
      l = 0;
      r = 0;
    }

    /**
     * 判断队列是否为空。
     *
     * @return 若 l==r 返回 true，否则 false
     */
    public boolean isEmpty() {
      return l == r;
    }

    /**
     * 向队列尾部添加元素。
     *
     * @param num 要添加的整数
     */
    public void offer(int num) {
      queue[r++] = num;
    }

    /**
     * 从队列头部移除并返回元素。
     *
     * @return 头部元素
     */
    public int poll() {
      return queue[l++];
    }

    /**
     * 查看队列头部元素但不移除。
     *
     * @return 头部元素
     */
    public int head() {
      return queue[l];
    }

    /**
     * 查看队列尾部元素但不移除。
     *
     * @return 尾部元素
     */
    public int tail() {
      return queue[r - 1];
    }

    /**
     * 返回当前队列长度。
     *
     * @return r-l
     */
    public int size() {
      return r - l;
    }
  }

  /**
   * 基于 Java 内置 Stack 的栈实现。
   *
   * <p>内部使用动态数组实现，均摊 O(1)。
   */
  public static class Stack1 {
    private final Stack<Integer> stack = new Stack<>();

    /**
     * 判断栈是否为空。
     *
     * @return true or false
     */
    public boolean isEmpty() {
      return stack.isEmpty();
    }

    /**
     * 入栈操作。
     *
     * @param num 要压入的整数
     */
    public void push(int num) {
      stack.push(num);
    }

    /**
     * 出栈并返回元素。
     *
     * @return 栈顶元素
     */
    public int pop() {
      return stack.pop();
    }

    /**
     * 查看栈顶元素但不弹出。
     *
     * @return 栈顶元素
     */
    public int peek() {
      return stack.peek();
    }

    /**
     * 返回当前栈大小。
     *
     * @return 元素个数
     */
    public int size() {
      return stack.size();
    }
  }

  /**
   * 基于固定大小数组的栈实现。
   *
   * <p>如果能明确最大深度 n，所有操作均为 O(1)。
   */
  public static class Stack2 {
    private final int[] stack;
    private int size;

    /**
     * 构造方法，指定最大容量。
     *
     * @param n 栈的最大深度
     */
    public Stack2(int n) {
      stack = new int[n];
      size = 0;
    }

    /**
     * 判断栈是否为空。
     *
     * @return true or false
     */
    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * 入栈操作。
     *
     * @param num 要压入的整数
     */
    public void push(int num) {
      stack[size++] = num;
    }

    /**
     * 出栈并返回元素。
     *
     * @return 栈顶元素
     */
    public int pop() {
      return stack[--size];
    }

    /**
     * 查看栈顶元素但不弹出。
     *
     * @return 栈顶元素
     */
    public int peek() {
      return stack[size - 1];
    }

    /**
     * 返回当前栈大小。
     *
     * @return 元素个数
     */
    public int size() {
      return size;
    }
  }

  /** 设计循环队列实现。 测试链接 : https://leetcode.cn/problems/design-circular-queue/ */
  public static class MyCircularQueue {
    private final int[] queue;
    private int l;
    private int r;
    private int size;
    private final int limit;

    /**
     * 构造方法，指定队列容量 k。
     *
     * @param k 队列最大容量
     */
    public MyCircularQueue(int k) {
      queue = new int[k];
      l = 0;
      r = 0;
      size = 0;
      limit = k;
    }

    /**
     * 入队操作，如果队满返回 false，否则入队并返回 true。
     *
     * @param value 要插入的整数
     * @return 是否入队成功
     */
    public boolean enQueue(int value) {
      if (isFull()) {
        return false;
      }
      queue[r] = value;
      r = (r + 1) % limit;
      size++;
      return true;
    }

    /**
     * 出队操作，如果队空返回 false，否则出队并返回 true。
     *
     * @return 是否出队成功
     */
    public boolean deQueue() {
      if (isEmpty()) {
        return false;
      }
      l = (l + 1) % limit;
      size--;
      return true;
    }

    /**
     * 查看队首元素。
     *
     * @return 队首元素或 -1
     */
    public int Front() {
      return isEmpty() ? -1 : queue[l];
    }

    /**
     * 查看队尾元素。
     *
     * @return 队尾元素或 -1
     */
    public int Rear() {
      return isEmpty() ? -1 : queue[(r - 1 + limit) % limit];
    }

    /**
     * 判断队列是否为空。
     *
     * @return true or false
     */
    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * 判断队列是否为满。
     *
     * @return true or false
     */
    public boolean isFull() {
      return size == limit;
    }
  }
}
