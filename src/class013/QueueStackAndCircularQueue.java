package class013;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueStackAndCircularQueue {

	// 直接用java内部的实现
	// 其实内部就是双向链表，常数操作慢
	public static class Queue1 {

		// java中的双向链表LinkedList
		// 单向链表就足够了
		public Queue<Integer> queue = new LinkedList<>();

		// 调用任何方法之前，先调用这个方法来判断队列内是否有东西
		public boolean isEmpty() {
			return queue.isEmpty();
		}

		// 向队列中加入num，加到尾巴
		public void offer(int num) {
			queue.offer(num);
		}

		// 从队列拿，从头拿
		public int poll() {
			return queue.poll();
		}

		// 返回队列头的元素但是不弹出
		public int peek() {
			return queue.peek();
		}

		// 返回目前队列里有几个数
		public int size() {
			return queue.size();
		}

	}

	// 实际刷题时更常见的写法，常数时间好
	// 如果可以确定加入操作的总次数不超过n，那么可以用
	// 一般笔试、面试都会有一个明确数据量，所以这是最常用的方式
	public static class Queue2 {

		public int[] queue;
		public int l;
		public int r;

		// 加入操作的总次数上限是多少，一定要明确
		public Queue2(int n) {
			queue = new int[n];
			l = 0;
			r = 0;
		}

		// 调用任何方法之前，先调用这个方法来判断队列内是否有东西
		public boolean isEmpty() {
			return l == r;
		}

		public void offer(int num) {
			queue[r++] = num;
		}

		public int poll() {
			return queue[l++];
		}
		// ?
		// l...r-1 r
		// [l..r)
		public int head() {
			return queue[l];
		}

		public int tail() {
			return queue[r - 1];
		}

		public int size() {
			return r - l;
		}

	}

	// 直接用java内部的实现
	// 其实就是动态数组，不过常数时间并不好
	public static class Stack1 {

		public Stack<Integer> stack = new Stack<>();

		// 调用任何方法之前，先调用这个方法来判断栈内是否有东西
		public boolean isEmpty() {
			return stack.isEmpty();
		}

		public void push(int num) {
			stack.push(num);
		}

		public int pop() {
			return stack.pop();
		}

		public int peek() {
			return stack.peek();
		}

		public int size() {
			return stack.size();
		}

	}

	// 实际刷题时更常见的写法，常数时间好
	// 如果可以保证同时在栈里的元素个数不会超过n，那么可以用
	// 也就是发生弹出操作之后，空间可以复用
	// 一般笔试、面试都会有一个明确数据量，所以这是最常用的方式
	public static class Stack2 {

		public int[] stack;
		public int size;

		// 同时在栈里的元素个数不会超过n
		public Stack2(int n) {
			stack = new int[n];
			size = 0;
		}

		// 调用任何方法之前，先调用这个方法来判断栈内是否有东西
		public boolean isEmpty() {
			return size == 0;
		}

		public void push(int num) {
			stack[size++] = num;
		}

		public int pop() {
			return stack[--size];
		}

		public int peek() {
			return stack[size - 1];
		}

		public int size() {
			return size;
		}
    }

    // 设计循环队列
    // 测试链接 : https://leetcode.cn/problems/design-circular-queue/

    class MyCircularQueue {
        private final int[] data;
        private final int capacity;
        private int head;
        private int tail;
        private int size;

        /**
         * Initializes the circular queue with the given capacity k.
         *
         * @param k the maximum number of elements the queue can hold
         */
        public MyCircularQueue(int k) {
            this.capacity = k;
            this.data = new int[k];
            this.head = 0;
            this.tail = 0;
            this.size = 0;
        }

        /**
         * Inserts an element into the circular queue.
         *
         * @param value the value to insert
         * @return true if the insertion is successful, false if the queue is full
         */
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            data[tail] = value;
            tail = (tail + 1) % capacity;
            size++;
            return true;
        }

        /**
         * Deletes an element from the circular queue.
         *
         * @return true if the deletion is successful, false if the queue is empty
         */
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % capacity;
            size--;
            return true;
        }

        /**
         * Gets the front item from the queue.
         *
         * @return the value at the front, or -1 if the queue is empty
         */
        public int Front() {
            return isEmpty() ? -1 : data[head];
        }

        /**
         * Gets the last item from the queue.
         *
         * @return the value at the rear, or -1 if the queue is empty
         */
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            // tail points to the next insertion index, so the last element is at tail-1
            int idx = (tail - 1 + capacity) % capacity;
            return data[idx];
        }

        /**
         * Checks whether the circular queue is empty.
         *
         * @return true if the queue is empty, false otherwise
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Checks whether the circular queue is full.
         *
         * @return true if the queue is full, false otherwise
         */
        public boolean isFull() {
            return size == capacity;
        }
    }
}
