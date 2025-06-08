package class037;

// 在普通二叉樹上尋找兩個節點的最近公共祖先（LCA）
// 測試連結 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class Code01_LowestCommonAncestor {

  /**
   * 後序遍歷 + 分治： 1. 遞迴遍歷左右子樹， 若遇到 null、p 或 q 即返回該節點。 2. 左右遞迴結果同時非空，說明 p、q 分別在左右子樹中， 當前 root 即為最近公共祖先。
   * 3. 若一方為空，返回另一方結果。
   *
   * <p>時間複雜度 O(n)，空間複雜度 O(h) （遞迴棧）。
   *
   * @param root 二叉樹根節點
   * @param p 目標節點 p
   * @param q 目標節點 q
   * @return 最近公共祖先節點
   */
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) {
      // p、q 分別位於 root 的左右子樹
      return root;
    }
    // 返回非空的一方 (可能是 p、q 中一個，或遞迴中找到的 LCA)
    return (left != null) ? left : right;
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
