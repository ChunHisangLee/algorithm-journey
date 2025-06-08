package class037;

import java.util.ArrayDeque;
import java.util.Deque;

// 驗證二叉搜尋樹
// 要求：對於每個節點，左子樹所有值 < 節點值 < 右子樹所有值
// 測試連結 : https://leetcode.cn/problems/validate-binary-search-tree/
public class Code05_ValidateBinarySearchTree {

  /**
   * 方法一：迭代中序遍歷 中序遍歷 BST 應該是嚴格遞增序列。 時間 O(n)，空間 O(h)
   *
   * @param root 樹根
   * @return 是否為有效 BST
   */
  public static boolean isValidBST(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode prev = null;
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      // 走到最左
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      // 檢查遞增性
      if (prev != null && curr.val <= prev.val) {
        return false;
      }
      prev = curr;
      // 轉向右子樹
      curr = curr.right;
    }
    return true;
  }

  /**
   * 方法二：遞歸邊界檢查 傳遞下界與上界，確保所有節點值在 (lower, upper) 範圍內。 時間 O(n)，空間 O(h)
   *
   * @param root 樹根
   * @return 是否為有效 BST
   */
  public static boolean isValidBSTRecursive(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private static boolean validate(TreeNode node, long lower, long upper) {
    if (node == null) {
      return true;
    }
    if (node.val <= lower || node.val >= upper) {
      return false;
    }
    // 左子樹上界為 node.val，右子樹下界為 node.val
    return validate(node.left, lower, node.val) && validate(node.right, node.val, upper);
  }

  // 標準二叉樹節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }
}
