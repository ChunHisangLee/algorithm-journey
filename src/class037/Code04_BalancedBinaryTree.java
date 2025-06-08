package class037;

// 驗證二叉樹是否為平衡二叉樹（每個節點的左右子樹高度差不超過 1）
// 測試連結 : https://leetcode.cn/problems/balanced-binary-tree/
public class Code04_BalancedBinaryTree {

  /**
   * 檢查二叉樹是否平衡 利用後序遍歷： - 遞迴計算左右子樹高度，若任一子樹不平衡則返回 -1 - 當子樹高度差 > 1 時也返回 -1 - 否則返回該子樹高度
   *
   * <p>時間複雜度 O(n)，空間複雜度 O(h)
   *
   * @param root 樹根
   * @return 是否為平衡樹
   */
  public static boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
  }

  /**
   * 遞迴計算子樹高度
   *
   * @param node 子樹根
   * @return 高度或 -1 表示該子樹不平衡
   */
  private static int checkHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int lh = checkHeight(node.left);
    if (lh == -1) {
      return -1; // 左子樹已不平衡
    }
    int rh = checkHeight(node.right);
    if (rh == -1) {
      return -1; // 右子樹已不平衡
    }
    if (Math.abs(lh - rh) > 1) {
      return -1; // 當前節點不平衡
    }
    return Math.max(lh, rh) + 1;
  }

  // 標準二叉樹節點
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }
}
