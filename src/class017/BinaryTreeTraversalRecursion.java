package class017;

// 递归序的解释
// 用递归实现二叉树的三序遍历
public class BinaryTreeTraversalRecursion {

	/**
	 * 递归基本样子，用来理解递归序。
	 *
	 * @param head 当前处理节点
	 */
	public static void f(TreeNode head) {
		if (head == null) {
			return;
		}
		// 1: 前处理
		f(head.left);
		// 2: 中处理
		f(head.right);
		// 3: 后处理
	}

	/**
	 * 先序遍历：根-左-右，递归实现。
	 *
	 * @param head 当前根节点
	 */
	public static void preOrder(TreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.val + " ");
		preOrder(head.left);
		preOrder(head.right);
	}

	/**
	 * 中序遍历：左-根-右，递归实现。
	 *
	 * @param head 当前根节点
	 */
	public static void inOrder(TreeNode head) {
		if (head == null) {
			return;
		}
		inOrder(head.left);
		System.out.print(head.val + " ");
		inOrder(head.right);
	}

	/**
	 * 后序遍历：左-右-根，递归实现。
	 *
	 * @param head 当前根节点
	 */
	public static void posOrder(TreeNode head) {
		if (head == null) {
			return;
		}
		posOrder(head.left);
		posOrder(head.right);
		System.out.print(head.val + " ");
	}

	/**
	 * 构造示例二叉树并演示三种遍历顺序。
	 */
	public static void main(String[] args) {
		TreeNode head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.right = new TreeNode(3);
		head.left.left = new TreeNode(4);
		head.left.right = new TreeNode(5);
		head.right.left = new TreeNode(6);
		head.right.right = new TreeNode(7);

		// 先序遍历
		preOrder(head);
		System.out.println();
		System.out.println("先序遍历递归版");

		// 中序遍历
		inOrder(head);
		System.out.println();
		System.out.println("中序遍历递归版");

		// 后序遍历
		posOrder(head);
		System.out.println();
		System.out.println("后序遍历递归版");
	}

	/**
	 * 二叉树节点定义。
	 */
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		/**
		 * 构造函数
		 *
		 * @param v 节点值
		 */
		public TreeNode(int v) {
			val = v;
		}
	}
}
