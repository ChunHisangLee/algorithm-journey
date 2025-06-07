package class018;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 不用递归，用迭代的方式实现二叉树的三序遍历
public class BinaryTreeTraversalIteration {

    /**
     * 先序打印所有节点，非递归版。
     *
     * @param head 树根节点
     */
	public static void preOrder(TreeNode head) {
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.val + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
			System.out.println();
        }
    }

    /**
     * 中序打印所有节点，非递归版。
     *
     * @param head 树根节点
	 */
	public static void inOrder(TreeNode head) {
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.val + " ");
					head = head.right;
				}
			}
			System.out.println();
        }
    }

    /**
     * 后序打印所有节点，非递归版。使用两个栈实现。
     *
     * @param head 树根节点
	 */
	public static void posOrderTwoStacks(TreeNode head) {
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			Stack<TreeNode> collect = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				collect.push(head);
				if (head.left != null) {
					stack.push(head.left);
				}
				if (head.right != null) {
					stack.push(head.right);
				}
			}
			while (!collect.isEmpty()) {
				System.out.print(collect.pop().val + " ");
			}
			System.out.println();
        }
    }

    /**
     * 后序打印所有节点，非递归版。使用一个栈实现。
     *
     * @param h 树根节点
	 */
	public static void posOrderOneStack(TreeNode h) {
		if (h != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(h);
            // h 保存上一个已打印节点
			while (!stack.isEmpty()) {
				TreeNode cur = stack.peek();
				if (cur.left != null && h != cur.left && h != cur.right) {
                    // 左子树未处理
					stack.push(cur.left);
				} else if (cur.right != null && h != cur.right) {
                    // 右子树未处理
					stack.push(cur.right);
				} else {
					System.out.print(cur.val + " ");
					h = stack.pop();
				}
			}
			System.out.println();
        }
    }

    /**
     * 构造示例二叉树并演示所有遍历方法。
	 */
	public static void main(String[] args) {
		TreeNode head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.right = new TreeNode(3);
		head.left.left = new TreeNode(4);
		head.left.right = new TreeNode(5);
		head.right.left = new TreeNode(6);
		head.right.right = new TreeNode(7);
		preOrder(head);
		System.out.println("先序遍历非递归版");
		inOrder(head);
		System.out.println("中序遍历非递归版");
		posOrderTwoStacks(head);
		System.out.println("后序遍历非递归版 - 2个栈实现");
		posOrderOneStack(head);
		System.out.println("后序遍历非递归版 - 1个栈实现");
    }

    /**
     * 用一个栈完成先序遍历，返回遍历结果列表。
     *
     * @param head 树根节点
     * @return 先序遍历节点值列表
	 */
	public static List<Integer> preorderTraversal(TreeNode head) {
		List<Integer> ans = new ArrayList<>();
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				ans.add(head.val);
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		return ans;
    }

    /**
     * 用一个栈完成中序遍历，返回遍历结果列表。
     *
     * @param head 树根节点
     * @return 中序遍历节点值列表
	 */
	public static List<Integer> inorderTraversal(TreeNode head) {
		List<Integer> ans = new ArrayList<>();
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					ans.add(head.val);
					head = head.right;
				}
			}
		}
		return ans;
    }

    /**
     * 用两个栈完成后序遍历，返回遍历结果列表。
     *
     * @param head 树根节点
     * @return 后序遍历节点值列表
	 */
	public static List<Integer> postorderTraversalTwoStacks(TreeNode head) {
		List<Integer> ans = new ArrayList<>();
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			Stack<TreeNode> collect = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				collect.push(head);
				if (head.left != null) {
					stack.push(head.left);
				}
				if (head.right != null) {
					stack.push(head.right);
				}
			}
			while (!collect.isEmpty()) {
				ans.add(collect.pop().val);
			}
		}
		return ans;
    }

    /**
     * 用一个栈完成后序遍历，返回遍历结果列表。
     *
     * @param h 树根节点
     * @return 后序遍历节点值列表
	 */
	public static List<Integer> postorderTraversalOneStack(TreeNode h) {
		List<Integer> ans = new ArrayList<>();
		if (h != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(h);
			while (!stack.isEmpty()) {
				TreeNode cur = stack.peek();
				if (cur.left != null && h != cur.left && h != cur.right) {
					stack.push(cur.left);
				} else if (cur.right != null && h != cur.right) {
					stack.push(cur.right);
				} else {
					ans.add(cur.val);
					h = stack.pop();
				}
			}
		}
		return ans;
    }

    /**
     * 二叉树节点定义。
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        /**
         * 构造函数，初始化节点值。
         *
         * @param v 节点值
         */
        public TreeNode(int v) {
            val = v;
		}
	}

}
