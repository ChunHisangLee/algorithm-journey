package class015;

import java.util.Stack;

// 最小栈
// 测试链接 : https://leetcode.cn/problems/min-stack/
public class GetMinStack {

  /**
   * 基于两个栈实现的最小栈。
   *
   * <p>data 栈保存所有元素，min 栈在每次 push 时同步保存当前最小值， 以保证 peek 和 getMin 均为 O(1)。
   */
  public static class MinStack1 {
    private final Stack<Integer> data;
    private final Stack<Integer> min;

    /** 构造方法，初始化数据栈和最小值栈。 */
    public MinStack1() {
      data = new Stack<>();
      min = new Stack<>();
    }

    /**
     * 添加元素并更新最小值栈。
     *
     * @param val 待添加的整数
     */
    public void push(int val) {
      data.push(val);
      if (min.isEmpty() || val <= min.peek()) {
        min.push(val);
      } else {
        min.push(min.peek());
      }
    }

    /** 删除栈顶元素，同时弹出最小值栈顶。 */
    public void pop() {
      data.pop();
      min.pop();
    }

    /**
     * 获取栈顶元素。
     *
     * @return 栈顶的整数
     */
    public int top() {
      return data.peek();
    }

    /**
     * 查询当前栈中的最小值。
     *
     * @return 最小值
     */
    public int getMin() {
      return min.peek();
    }
  }

  /**
   * 基于数组实现的最小栈。
   *
   * <p>假设最多有 MAXN 个元素入栈，使用同步数组保存最小值。
   */
  public static class MinStack2 {
    /** 最大容量，根据测试情况调整。 */
    private static final int MAXN = 8001;

    private final int[] data;
    private final int[] min;
    private int size;

    /** 构造方法，初始化容量和指针。 */
    public MinStack2() {
      data = new int[MAXN];
      min = new int[MAXN];
      size = 0;
    }

    /**
     * 添加元素并更新最小值数组。
     *
     * @param val 待添加的整数
     */
    public void push(int val) {
      data[size] = val;
      if (size == 0 || val <= min[size - 1]) {
        min[size] = val;
      } else {
        min[size] = min[size - 1];
      }
      size++;
    }

    /** 删除栈顶元素。 */
    public void pop() {
      size--;
    }

    /**
     * 获取栈顶元素。
     *
     * @return 栈顶的整数
     */
    public int top() {
      return data[size - 1];
    }

    /**
     * 查询当前最小值。
     *
     * @return 当前栈中的最小值
     */
    public int getMin() {
      return min[size - 1];
    }
  }
}
