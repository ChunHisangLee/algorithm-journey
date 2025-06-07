package class036;

// 計算完全二叉樹的節點總數
// 要求：時間複雜度 O((log n)^2)，空間複雜度 O(1)
// 測試連結 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code09_CountCompleteTreeNodes {

  /**
   * 主入口：先計算整棵樹高度 h，然後遞迴統計
   *
   * @param root 樹根
   * @return 節點總數
   */
  public static int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int h = treeHeight(root);
    return countRec(root, 1, h);
  }

  /**
   * 遞迴計算以 cur 為根的子樹中節點數量
   *
   * @param cur 當前子樹根
   * @param level 當前節點所在層級 (根為第 1 層)
   * @param h 樹高度
   * @return 該子樹的節點總數
   */
  private static int countRec(TreeNode cur, int level, int h) {
    if (level == h) {
      // 到達最後一層，只有一個節點
      return 1;
    }
    // 判斷右子樹的最左節點是否到達最深層
    if (treeHeight(cur.right) == h - level) {
      // 左子樹為滿二叉樹，高度 h-level，節點數為 (2^(h-level) - 1)
      // 加上根節點，再加上右子樹節點
      return (1 << (h - level)) + countRec(cur.right, level + 1, h);
    } else {
      // 右子樹高度小於 h-level-1，右子樹為滿二叉樹，高度 h-level-1
      return (1 << (h - level - 1)) + countRec(cur.left, level + 1, h);
    }
  }

  /**
   * 計算從 root 開始往最左一直深入，可到達的最大層級
   *
   * @param root 節點
   * @return 層級數 (根為第 1 層)
   */
  private static int treeHeight(TreeNode root) {
    int h = 0;
    while (root != null) {
      h++;
      root = root.left;
    }
    return h;
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
