package class016;

import java.util.Deque;
import java.util.LinkedList;

// 设计循环双端队列
// 测试链接 : https://leetcode.cn/problems/design-circular-deque/
public class CircularDeque {

	// 提交时把类名、构造方法改成 : MyCircularDeque
	// 其实内部就是双向链表
	// 常数操作慢，但是leetcode数据量太小了，所以看不出劣势

    /**
     * 基于 LinkedList 实现的循环双端队列。
     * <p>
     * 操作均为常数时间复杂度，但由于链表节点分配，常数开销稍大。
     */
	class MyCircularDeque1 {

		public Deque<Integer> deque = new LinkedList<>();
		public int size;
		public int limit;

        /**
         * 构造函数，初始化容量。
         *
         * @param k 最大元素个数
		 */
		public MyCircularDeque1(int k) {
			size = 0;
			limit = k;
        }

        /**
         * 将一个元素添加到双端队列头部。
         *
         * @param value 待插入值
         * @return 成功插入返回 true；队列已满返回 false
		 */
		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
            }
            deque.offerFirst(value);
            size++;
            return true;
        }

        /**
         * 将一个元素添加到双端队列尾部。
         *
         * @param value 待插入值
         * @return 成功插入返回 true；队列已满返回 false
		 */
		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
            }
            deque.offerLast(value);
            size++;
            return true;
        }

        /**
         * 从双端队列头部删除一个元素。
         *
         * @return 成功删除返回 true；队列为空返回 false
		 */
		public boolean deleteFront() {
			if (isEmpty()) {
				return false;
            }
            deque.pollFirst();
            size--;
            return true;
        }

        /**
         * 从双端队列尾部删除一个元素。
         *
         * @return 成功删除返回 true；队列为空返回 false
		 */
		public boolean deleteLast() {
			if (isEmpty()) {
				return false;
            }
            deque.pollLast();
            size--;
            return true;
        }

        /**
         * 获取双端队列头部元素。
         *
         * @return 头部元素；队列为空时返回 -1
		 */
		public int getFront() {
            return isEmpty() ? -1 : deque.peekFirst();
        }

        /**
         * 获取双端队列尾部元素。
         *
         * @return 尾部元素；队列为空时返回 -1
		 */
		public int getRear() {
            return isEmpty() ? -1 : deque.peekLast();
        }

        /**
         * 检查双端队列是否为空。
         *
         * @return 为空时返回 true，否则返回 false
		 */
		public boolean isEmpty() {
			return size == 0;
        }

        /**
         * 检查双端队列是否已满。
         *
         * @return 已满时返回 true，否则返回 false
		 */
		public boolean isFull() {
			return size == limit;
		}
	}

	// 提交时把类名、构造方法改成 : MyCircularDeque
	// 自己用数组实现，常数操作快，但是leetcode数据量太小了，看不出优势

    /**
     * 基于数组实现的循环双端队列。
     *
     * 所有操作均为 O(1)。
	 */
	class MyCircularDeque2 {

		public int[] deque;
        public int l;
        public int r;
        public int size;
        public int limit;

        /**
         * 构造函数，初始化数组和指针。
         *
         * @param k 最大元素个数
		 */
		public MyCircularDeque2(int k) {
			deque = new int[k];
            l = 0;
            r = 0;
            size = 0;
			limit = k;
        }

        /**
         * 将一个元素添加到双端队列头部。
         *
         * @param value 待插入值
         * @return 成功插入返回 true；队列已满返回 false
		 */
		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
            }
            l = (l - 1 + limit) % limit;
            deque[l] = value;
            size++;
            return true;
        }

        /**
         * 将一个元素添加到双端队列尾部。
         *
         * @param value 待插入值
         * @return 成功插入返回 true；队列已满返回 false
		 */
		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
            }
            deque[r] = value;
            r = (r + 1) % limit;
            size++;
            return true;
        }

        /**
         * 从双端队列头部删除一个元素。
         *
         * @return 成功删除返回 true；队列为空返回 false
		 */
		public boolean deleteFront() {
			if (isEmpty()) {
				return false;
            }
            l = (l + 1) % limit;
            size--;
            return true;
        }

        /**
         * 从双端队列尾部删除一个元素。
         *
         * @return 成功删除返回 true；队列为空返回 false
		 */
		public boolean deleteLast() {
			if (isEmpty()) {
				return false;
            }
            r = (r - 1 + limit) % limit;
            size--;
            return true;
        }

        /**
         * 获取双端队列头部元素。
         *
         * @return 头部元素；队列为空时返回 -1
		 */
		public int getFront() {
            return isEmpty() ? -1 : deque[l];
        }

        /**
         * 获取双端队列尾部元素。
         *
         * @return 尾部元素；队列为空时返回 -1
		 */
		public int getRear() {
            return isEmpty() ? -1 : deque[(r - 1 + limit) % limit];
        }

        /**
         * 检查双端队列是否为空。
         *
         * @return 为空时返回 true，否则返回 false
		 */
		public boolean isEmpty() {
			return size == 0;
        }

        /**
         * 检查双端队列是否已满。
         *
         * @return 已满时返回 true，否则返回 false
		 */
		public boolean isFull() {
			return size == limit;
		}
	}
}
