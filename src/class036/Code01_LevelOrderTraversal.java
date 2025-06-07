package class036;

import java.util.*;

// 二叉樹的層序遍歷
// 測試連結 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
// 提交時請將方法名改為 levelOrder
public class Code01_LevelOrderTraversal {

  /** 方法一：使用 Java 內建隊列與 Map 記錄層級（普通 BFS，不推薦） 時間複雜度 O(n)，空間複雜度 O(n) */
  public static List<List<Integer>> levelOrder1(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    Deque<TreeNode> queue = new ArrayDeque<>();
    // levels 紀錄每個節點所屬層級
    Map<TreeNode, Integer> levels = new HashMap<>();
    queue.offer(root);
    levels.put(root, 0);

    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      int level = levels.get(cur);
      if (ans.size() == level) {
        ans.add(new ArrayList<>());
      }
      ans.get(level).add(cur.val);
      if (cur.left != null) {
        queue.offer(cur.left);
        levels.put(cur.left, level + 1);
      }
      if (cur.right != null) {
        queue.offer(cur.right);
        levels.put(cur.right, level + 1);
      }
    }
    return ans;
  }

  /** 方法二：優化版 BFS，每次處理一整層，推薦使用 時間複雜度 O(n)，空間複雜度 O(n) */
  public static List<List<Integer>> levelOrder2(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int sz = queue.size();
      ArrayList<Integer> levelList = new ArrayList<>(sz);
      for (int i = 0; i < sz; i++) {
        TreeNode cur = queue.poll();
        levelList.add(cur.val);
        if (cur.left != null) queue.offer(cur.left);
        if (cur.right != null) queue.offer(cur.right);
      }
      ans.add(levelList);
    }
    return ans;
  }

  // 標準二叉樹節點
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }
}
