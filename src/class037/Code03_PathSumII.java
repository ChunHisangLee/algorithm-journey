package class037;

import java.util.ArrayList;
import java.util.List;

// 收集所有從根節點到葉節點且路徑和等於目標值的路徑列表
// 測試連結 : https://leetcode.cn/problems/path-sum-ii/
public class Code03_PathSumII {

  /**
   * 主方法： - 使用 DFS + 回溯，在遍歷時維護當前路徑與累計和 - 到達葉節點時，若累計和等於目標，則將當前路徑加入結果
   *
   * <p>時間複雜度：O(n * h)，其中 n 是節點數，h 是樹高度（為了複製路徑列表） 空間複雜度：O(h^2)（遞歸棧 + 最壞情況下存儲所有路徑複製）
   *
   * @param root 樹根
   * @param targetSum 目標路徑和
   * @return 所有符合條件的路徑列表
   */
  public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    List<Integer> path = new ArrayList<>();
    dfs(root, targetSum, 0, path, result);
    return result;
  }

  /**
   * 深度優先搜索回溯函式
   *
   * @param node 當前節點
   * @param targetSum 目標總和
   * @param currentSum 當前累計和
   * @param path 當前路徑上的節點值列表
   * @param result 符合條件的路徑列表集合
   */
  private static void dfs(
      TreeNode node,
      int targetSum,
      int currentSum,
      List<Integer> path,
      List<List<Integer>> result) {
    if (node == null) {
      return;
    }
    // 走入當前節點
    currentSum += node.val;
    path.add(node.val);

    // 如果是葉節點，檢查總和
    if (node.left == null && node.right == null) {
      if (currentSum == targetSum) {
        // 直接用 ArrayList 的構造複製路徑
        result.add(new ArrayList<>(path));
      }
    } else {
      // 繼續向下
      dfs(node.left, targetSum, currentSum, path, result);
      dfs(node.right, targetSum, currentSum, path, result);
    }

    // 回溯：移除當前節點，返回上一層
    path.remove(path.size() - 1);
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
