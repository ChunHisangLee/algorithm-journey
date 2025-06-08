package class037;

// 在二叉搜尋樹上尋找兩個節點的最近公共祖先（LCA）
// 採用直接比較，推薦在面試中使用
public class Code02_LowestCommonAncestorBinarySearch {

  /**
   * BST 特性：左子樹 < 根 < 右子樹 迭代法： - 若 root.val 大於 p.val 和 q.val，答案在左側 - 若 root.val 小於 p.val 和
   * q.val，答案在右側 - 否則 root 就是最近公共祖先
   *
   * @param root BST 根節點
   * @param p 節點 p
   * @param q 節點 q
   * @return LCA 節點
   */
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
      if (root.val > p.val && root.val > q.val) {
        // p, q 均在左子樹
        root = root.left;
      } else if (root.val < p.val && root.val < q.val) {
        // p, q 均在右子樹
        root = root.right;
      } else {
        // root 位於 p 和 q 之間，或等於其中一個，即為 LCA
        return root;
      }
    }
    return null;
  }

  // 標準 BST 節點
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }
}
