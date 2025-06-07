package class036;

import java.util.Queue;
import java.util.LinkedList;

// 計算二叉樹最大及最小深度
// 最大深度測試連結 : https://leetcode.cn/problems/maximum-depth-of-binary-tree/
// 最小深度測試連結 : https://leetcode.cn/problems/minimum-depth-of-binary-tree/
public class Code04_DepthOfBinaryTree {

  /**
   * 計算二叉樹最大深度，遞歸實現 時間複雜度 O(n)，空間複雜度 O(h)
   *
   * @param root 樹根
   * @return 最大深度
   */
  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  /**
   * 計算二叉樹最小深度，BFS 層序遍歷實現 第一個遇到的葉節點所在層就是最小深度 時間複雜度 O(n)，空間複雜度 O(n)
   *
   * @param root 樹根
   * @return 最小深度
   */
  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int depth = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // 遇到葉節點，立即返回
        if (node.left == null && node.right == null) {
          return depth;
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      depth++;
    }
    return depth;
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
