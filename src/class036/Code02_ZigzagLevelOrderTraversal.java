package class036;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Collections;

// 二叉樹的鋸齒形層序遍歷
// 要求：沿每層順序交替進行從左到右及從右到左的遍歷
// 測試連結 : https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class Code02_ZigzagLevelOrderTraversal {

  /** 使用 BFS，每層收集節點值後根據旗標做順序或反序 時間複雜度 O(n)，空間複雜度 O(n) */
  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    boolean reverse = false; // false: L->R, true: R->L

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>(size);
      // 收集層內值
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        level.add(node.val);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }
      // 若需反序，使用 Collections.reverse
      if (reverse) {
        Collections.reverse(level);
      }
      ans.add(level);
      reverse = !reverse;
    }
    return ans;
  }

  // 標準二叉樹節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }
}
