package class036;

import java.util.LinkedList;
import java.util.Queue;

// 驗證完全二叉樹
// 給定一棵二叉樹，檢查是否為完全二叉樹
// 測試連結 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class Code08_CompletenessOfBinaryTree {

  /**
   * 完全二叉樹檢查： 1. 對樹進行 BFS，將節點（包括 null）加入隊列 2. 當首次遇到 null 後，隊列中不應再有非 null 節點 3. 若出現，則不是完全二叉樹
   *
   * @param root 樹根
   * @return 是否為完全二叉樹
   */
  public static boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean seenNull = false;
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node == null) {
        seenNull = true;
      } else {
        if (seenNull) {
          // 遇到過 null，卻又出現非空節點，違反完全二叉樹定義
          return false;
        }
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }
    return true;
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
