package class015;

import java.util.ArrayDeque;
import java.util.Deque;

// 最小栈
// 测试链接 : https://leetcode.cn/problems/min-stack/
public class GetMinStack {

	// 提交时把类名、构造方法改成MinStack
    class MinStack {
        private final Deque<Integer> stack;
        private final Deque<Integer> minStack;

        /**
         * Initialize your data structure here.
         */
        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        /**
         * Pushes the element val onto the stack.
         *
         * @param val the value to push
		 */
		public void push(int val) {
            stack.push(val);
            // If minStack is empty or new val is <= current min, push onto minStack
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        /**
         * Removes the element on the top of the stack.
		 */
		public void pop() {
            int removed = stack.pop();
            // If the popped value equals the current minimum, pop it from minStack too
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }

        /**
         * Gets the top element.
         *
         * @return the element on top of the stack
		 */
		public int top() {
            return stack.peek();
        }

        /**
         * Retrieves the minimum element in the stack.
         *
         * @return the minimum element
		 */
		public int getMin() {
            return minStack.peek();
    }
  }

	// 提交时把类名、构造方法改成MinStack
	class MinStack2 {
		// leetcode的数据在测试时，同时在栈里的数据不超过这个值
		// 这是几次提交实验出来的，哈哈
		// 如果leetcode补测试数据了，超过这个量导致出错，就调大
		public final int MAXN = 8001;

		public int[] data;
		public int[] min;
		int size;

		public MinStack2() {
			data = new int[MAXN];
			min = new int[MAXN];
			size = 0;
		}

		public void push(int val) {
			data[size] = val;
			if (size == 0 || val <= min[size - 1]) {
				min[size] = val;
			} else {
				min[size] = min[size - 1];
			}
			size++;
		}

		public void pop() {
			size--;
		}

		public int top() {
			return data[size - 1];
		}

		public int getMin() {
			return min[size - 1];
		}
	}

}
