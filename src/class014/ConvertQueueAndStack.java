package class014;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 用栈实现队列
// 用队列实现栈
/** 演示两种经典数据结构互相实现： - 用两个栈实现队列 - 用一个队列实现栈 */
public class ConvertQueueAndStack {

  // 直接测试链接 : https://leetcode.cn/problems/implement-queue-using-stacks/
  /** 用栈实现的队列。 通过两个栈分别承担入队和出队职责，实现 FIFO。 */
  public static class MyQueue {

    private final Stack<Integer> in;
    private final Stack<Integer> out;

    public MyQueue() {
      in = new Stack<>();
      out = new Stack<>();
    }

    /** 将 in 栈中的所有元素倒入 out 栈，以准备出队操作。 仅当 out 为空时执行，确保摊销 O(1) 性能。 */
    private void inToOut() {
      if (out.empty()) {
        while (!in.empty()) {
          out.push(in.pop());
        }
      }
    }

    /**
     * 入队操作：压入 in 栈并尝试倒数据到 out。
     *
     * @param x 要入队的元素
     */
    public void push(int x) {
      in.push(x);
      inToOut();
    }

    /**
     * 出队操作：确保 out 栈有序后弹出栈顶。
     *
     * @return 队首元素
     */
    public int pop() {
      inToOut();
      return out.pop();
    }

    /**
     * 获取队首元素但不出队。
     *
     * @return 队首元素
     */
    public int peek() {
      inToOut();
      return out.peek();
    }

    /**
     * 判断队列是否为空。
     *
     * @return 若 in 和 out 均为空，则队列为空
     */
    public boolean empty() {
      return in.isEmpty() && out.isEmpty();
    }
  }

  // 直接测试链接 : https://leetcode.cn/problems/implement-stack-using-queues/
  /** 用队列实现的栈。 通过每次入队后将队头元素移动归位，实现 LIFO。 */
  public static class MyStack {

    private final Queue<Integer> queue;

    public MyStack() {
      queue = new LinkedList<>();
    }

    /**
     * 入栈操作：先入队，然后将前面所有元素依次出队再入队， 保证最新元素始终位于队头。
     *
     * @param x 要入栈的元素
     */
    public void push(int x) {
      int n = queue.size();
      queue.offer(x);
      for (int i = 0; i < n; i++) {
        queue.offer(queue.poll());
      }
    }

    /**
     * 出栈：直接从队头弹出。
     *
     * @return 栈顶元素
     */
    public int pop() {
      return queue.poll();
    }

    /**
     * 获取栈顶元素但不弹出。
     *
     * @return 栈顶元素
     */
    public int top() {
      return queue.peek();
    }

    /**
     * 判断栈是否为空。
     *
     * @return 队列为空则栈为空
     */
    public boolean empty() {
      return queue.isEmpty();
    }
  }
}
