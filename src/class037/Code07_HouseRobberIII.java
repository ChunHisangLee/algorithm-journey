package class037;

// 樹形打家劫舍 III
// 測試連結 : https://leetcode.cn/problems/house-robber-iii/
public class Code07_HouseRobberIII {

  /**
   * 入口方法：返回能獲得的最大金額
   *
   * @param root 樹根
   * @return 最大盜取金額
   */
  public static int rob(TreeNode root) {
    int[] res = dfs(root);
    // res[0] = 偷 root 的最大收益, res[1] = 不偷 root 的最大收益
    return Math.max(res[0], res[1]);
  }

  /**
   * 深度優先遞迴，返回長度為 2 的陣列： - res[0]：偷當前節點時最大收益 - res[1]：不偷當前節點時最大收益
   *
   * @param node 當前節點
   * @return 長度為2的結果陣列
   */
  private static int[] dfs(TreeNode node) {
    if (node == null) {
      return new int[] {0, 0};
    }
    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    // 偷當前節點：子節點都不能偷
    int robCur = node.val + left[1] + right[1];
    // 不偷當前節點：子節點可偷可不偷，取最大
    int notRobCur = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

    return new int[] {robCur, notRobCur};
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
